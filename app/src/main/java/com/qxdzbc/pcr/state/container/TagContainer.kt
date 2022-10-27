package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.firestore.TagDoc
import com.qxdzbc.pcr.state.model.Tag

interface TagContainer: Map<String, Tag> {
    val allTags:List<Tag>

    fun loadFromDbAndOverwrite(): TagContainer
    suspend fun susLoadFromDbAndOverWrite(): TagContainer

    /**
     * Update tag table so that it reflects the tags in this container
     */
    fun writeToDb():Rs<Unit, ErrorReport>
    suspend fun susWriteToDb():Rs<Unit, ErrorReport>

    suspend fun loadFromFirestoreAndOverwrite(userId:String):Rs<TagContainer,ErrorReport>

    suspend fun writeToFirestore(userId:String):Rs<Unit, ErrorReport>

    suspend fun initLoad(userId:String?):TagContainer
}
