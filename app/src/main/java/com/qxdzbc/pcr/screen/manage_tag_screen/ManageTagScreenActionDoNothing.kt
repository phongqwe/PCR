package com.qxdzbc.pcr.screen.manage_tag_screen

import com.qxdzbc.pcr.state.model.Tag

object ManageTagScreenActionDoNothing : ManageTagScreenAction {
    override suspend fun edit(oldTag: Tag, newTag: Tag) {
    }

    override suspend fun delete(tag: Tag) {
    }

    override suspend fun addTag(tag: Tag) {

    }
}
