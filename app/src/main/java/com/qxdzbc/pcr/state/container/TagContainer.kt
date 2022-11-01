package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.state.model.WriteState

interface TagContainer: Map<String, Tag> {
    val allTags:List<Tag>

    val allValidTags get()=allTags.filter { it.writeState!=WriteState.DeletePending }

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

    /**
     * Add tag and write to db
     */
    suspend fun addTagAndWriteToDb(tag: Tag): TagContainer

    /**
     * upload all the WritePending tags to firestore
     */
    suspend fun uploadThePendings(): TagContainer
    suspend fun deleteAndWriteToDb(tag: Tag): TagContainer
    suspend fun deleteThePendings(): TagContainer
    fun bluntRemoveTag(tag: Tag): TagContainer
    suspend fun replaceAndWriteToDbRs(tag: Tag): Rs<TagContainer, ErrorReport>
}
