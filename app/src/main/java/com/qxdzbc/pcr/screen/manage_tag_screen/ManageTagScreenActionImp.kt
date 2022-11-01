package com.qxdzbc.pcr.screen.manage_tag_screen

import androidx.compose.runtime.getValue
import com.github.michaelbull.result.mapBoth
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
    val ecMs: Ms<EntryContainer>
) : ManageTagScreenAction {
    val ec by ecMs
    val tc by tcMs
    override suspend fun edit(oldTag: Tag, newTag: Tag) {
        runOnDefaultDispatcher {
            val newNewTag = newTag.setWriteState(WriteState.WritePending)
            val tc2 = tc.replaceAndWriteToDbRs(newNewTag).mapBoth(
                success = { it },
                failure = { tc }
            )
            tcMs.value = tc2
            val tc3 = tc2.uploadThePendings()
            tcMs.value = tc3

            tc3.get(newTag.tagId)?.also {nt->
                // update related entries
                val targetEntries = ec.allEntries.filter {
                    it.tags.map { it.tagId }.contains(nt.tagId)
                }
                val newEntries = targetEntries.map {
                    val newTags = it.tags.map { t ->
                        if (t.tagId == nt.tagId) {
                            nt
                        } else {
                            t
                        }
                    }
                    it.setTags(newTags).setWriteState(WriteState.WritePending)
                }
                val ec2 = ec.addOrReplaceAndWriteToDb(newEntries)
                ecMs.value = ec2
                val ec3 = ec2.writePendingToFirestore()
                ecMs.value = ec3
            }
        }
    }

    override suspend fun delete(tag: Tag) {
        runOnDefaultDispatcher {
            val n = tc.deleteAndWriteToDb(tag)
            tcMs.value = n
            val n2 = n.deleteThePendings()
            tcMs.value = n2

            // remove tag from entries and update entry container
            val targetEntries = ec.allEntries.filter {
                it.tags.map { it.tagId }.contains(tag.tagId)
            }
            val newEntries = targetEntries.map {
                val newTags = it.tags.filter { it.tagId != tag.tagId }
                it.setTags(newTags).setWriteState(WriteState.WritePending)
            }
            val ec2 = ec.addOrReplaceAndWriteToDb(newEntries)
            ecMs.value = ec2
            val ec3 = ec2.writePendingToFirestore()
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
            val newCont = tc.addTagAndWriteToDb(tag)
            tcMs.value = newCont
            tcMs.value = tc.uploadThePendings()
        }
    }
}
