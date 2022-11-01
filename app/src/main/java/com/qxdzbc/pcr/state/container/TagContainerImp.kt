package com.qxdzbc.pcr.state.container

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapBoth
import com.qxdzbc.pcr.common.ResultUtils.toErr
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.database.DbErrors
import com.qxdzbc.pcr.database.dao.TagDao
import com.qxdzbc.pcr.di.DefaultTagMap
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.firestore.FirestoreHelper
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.state.model.WriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class TagContainerImp @Inject constructor(
    @DefaultTagMap
    private val m: Map<String, @JvmSuppressWildcards Tag>,
    private val tagDao: TagDao,
    private val firestoreHelper: FirestoreHelper
) : AbsTagContainer(m) {

    override val allTags: List<Tag>
        get() = m.values.toList()


    override fun loadFromDbAndOverwrite(): TagContainer {
        val m = tagDao.getAll().associateBy { it.id }
        return this.copy(m = m)
    }

    override suspend fun susLoadFromDbAndOverWrite(): TagContainer {
        return withContext(Dispatchers.Default) {
            loadFromDbAndOverwrite()
        }
    }

    override fun writeToDb(): Rs<Unit, ErrorReport> {
        try {
            tagDao.insertOrUpdate(this.allTags.map { it.toDbTag() })
            return Ok(Unit)
        } catch (e: Throwable) {
            val msg = "Unable to write tag in TagContainer into db"
            return DbErrors.UnableToWriteTagToDb.report(msg).toErr()
        }
    }

    override suspend fun susWriteToDb(): Rs<Unit, ErrorReport> {
        return withContext(Dispatchers.Default) {
            writeToDb()
        }
    }

    override suspend fun loadFromFirestoreAndOverwrite(userId: String): Rs<TagContainer, ErrorReport> {
        val rt = firestoreHelper.readAllTagsToModel(userId).map {
            this.copy(m = it.associateBy { it.tagId })
        }
        return rt
    }

    override suspend fun writeToFirestore(userId: String): Rs<Unit, ErrorReport> {
        return firestoreHelper.writeMultiTags(userId, allTags)
    }

    override suspend fun initLoad(userId: String?): TagContainer {
        val tCont = susLoadFromDbAndOverWrite()
        val rt = userId?.let { uid ->
            if (tCont.isEmpty()) {
                tCont.loadFromFirestoreAndOverwrite(uid).component1() ?: tCont
            } else {
                tCont
            }
        } ?: tCont
        return rt
    }

    private fun bluntReplace(tag: Tag): TagContainerImp {
        val m2 = m.toMutableMap()
        m2[tag.tagId] = tag
        return this.copy(m = m2)
    }

    override suspend fun uploadThePendings(): TagContainer {
        val targets = this.m.filter { (id, tag) -> tag.writeState == WriteState.WritePending }
            .map { it.value }

        val rt = firestoreHelper.writeMultiTags(targets.map { it.toDbTag() })
            .mapBoth(
                success = {
                    val newCont = targets.map { it.setWriteState(WriteState.OK) }
                        .fold(this) { acc: TagContainerImp, tag: Tag ->
                            acc.bluntReplace(tag)
                        }
                    val dbRs = tagDao.insertOrUpdateRs(newCont.allTags.map { it.toDbTag() })
                    dbRs.mapBoth(
                        success = {
                            newCont
                        },
                        failure = {
                            this
                        }
                    )
                },
                failure = {
                    this
                }
            )
        return rt
    }
    private fun replaceAndWriteToDbRs(tag:Tag):Rs<TagContainerImp,ErrorReport>{
        val replacedCont = this.bluntReplace(tag)
        val rt = replacedCont.writeToDb().map {
            replacedCont
        }
        return rt
    }
    override suspend fun deleteAndWriteToDb(tag: Tag): TagContainer {
        val markedTag = tag.setWriteState(WriteState.DeletePending)
        val afterMarkingContRs = this.replaceAndWriteToDbRs(tag.setWriteState(WriteState.DeletePending))
        val rt = afterMarkingContRs.mapBoth(
            success = {afterMarkingCont->
                tagDao.deleteRs(markedTag).mapBoth(
                    success = {
                        afterMarkingCont.bluntRemoveTag(markedTag)
                    },
                    failure = {
                        afterMarkingCont
                    }
                )
            },
            failure = {
                this
            }
        )
        return rt
    }

    override fun bluntRemoveTag(tag: Tag): TagContainerImp {
        return this.copy(m = m - tag.tagId)
    }

    override suspend fun deleteThePendings(): TagContainer {
        val targets = this.allTags.filter { it.writeState == WriteState.DeletePending }
        val rt = firestoreHelper.removeMultiTag(targets).mapBoth(
            success = {
                tagDao.deleteMultiRs(targets.map { it.toDbTag() }).mapBoth(
                    success = {
                        val afterDbCont = targets.fold(this) { acc, ttag ->
                            acc.bluntRemoveTag(ttag)
                        }
                        afterDbCont
                    },
                    failure = {
                        this //reflect the state of the db
                    }
                )
            },
            failure = {
                this
            },
        )
        return rt
    }

    override suspend fun addTagAndWriteToDb(tag: Tag): TagContainer {
        val o = this
        val rt: TagContainer = tagDao.insertOrUpdateRs(listOf(tag.toDbTag())).mapBoth(
            success = {
                val tag2 = tag.setWriteState(WriteState.WritePending)
                val afterDbCont = this.copy(m = m + (tag2.tagId to tag2))
                afterDbCont
            },
            failure = {
                o
            }
        )
        return rt
    }
}

