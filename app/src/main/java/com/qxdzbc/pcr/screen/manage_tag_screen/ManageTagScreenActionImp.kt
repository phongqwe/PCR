package com.qxdzbc.pcr.screen.manage_tag_screen

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.state.TagContMs
import com.qxdzbc.pcr.state.container.TagContainer
import com.qxdzbc.pcr.state.model.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ManageTagScreenActionImp @Inject constructor(
    @TagContMs
    val tcMs:Ms<TagContainer>
): ManageTagScreenAction {
    override suspend fun edit(oldTag: Tag, newTag: Tag) {
//        TODO("Not yet implemented")
    }

    override suspend fun delete(tag: Tag) {
//        TODO("Not yet implemented")
    }

    override suspend fun addTag(tag: Tag) {
        withContext(Dispatchers.Default){
            val newCont = tcMs.value.addTagAndWriteToDb(tag)
            tcMs.value = newCont
        }
    }
}
