package com.qxdzbc.pcr.screen.manage_tag_screen

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.EntryContMs
import com.qxdzbc.pcr.di.state.TagContMs
import com.qxdzbc.pcr.state.container.EntryContainer
import com.qxdzbc.pcr.state.container.TagContainer
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.state.model.WriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ManageTagScreenActionImp @Inject constructor(
    @TagContMs
    val tcMs: Ms<TagContainer>,
    @EntryContMs
    val ecMs:Ms<EntryContainer>
) : ManageTagScreenAction {
    override suspend fun edit(oldTag: Tag, newTag: Tag) {
//        TODO("Not yet implemented")
    }

    override suspend fun delete(tag: Tag) {
        runOnDefaultDispatcher {

            val n = tcMs.value.deleteAndWriteToDb(tag)
            tcMs.value = n
            val n2 = n.deleteThePendings()
            tcMs.value = n2

            // remove tag from entries
            val ec=ecMs.value
            val targetEntries = ec.allEntries.filter {
                it.tags.map { it.tagId }.contains(tag.tagId)
            }
            val newEntries=targetEntries.map {
                val newTags = it.tags.filter { it.tagId!=tag.tagId }
                it.setTags(newTags).setWriteState(WriteState.WritePending)
            }
            val ec2 = ecMs.value.addOrReplaceAndWriteToDb(newEntries)
            ecMs.value = ec2
            val ec3 = ec2.writeUnUploadedToFirestore()
            ecMs.value = ec3
        }
    }

    private suspend fun runOnDefaultDispatcher(f: suspend () -> Unit) {
        withContext(Dispatchers.Default) {
            f()
        }
    }

    override suspend fun addTag(tag: Tag) {
        withContext(Dispatchers.Default) {
            val newCont = tcMs.value.addTagAndWriteToDb(tag)
            tcMs.value = newCont
            tcMs.value = tcMs.value.uploadThePendings()
        }
    }
}
