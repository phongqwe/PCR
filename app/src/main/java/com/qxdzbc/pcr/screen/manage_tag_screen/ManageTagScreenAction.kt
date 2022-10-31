package com.qxdzbc.pcr.screen.manage_tag_screen

import com.qxdzbc.pcr.state.model.Tag

interface ManageTagScreenAction{
    suspend fun edit(oldTag:Tag,newTag:Tag)
    suspend fun delete(tag:Tag)
    suspend fun addTag(tag:Tag)
    companion object{
        val forPreview = ManageTagScreenActionDoNothing
    }
}
