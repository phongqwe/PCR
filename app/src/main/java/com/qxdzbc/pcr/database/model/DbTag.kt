package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qxdzbc.pcr.firestore.TagDoc
import com.qxdzbc.pcr.state.model.Tag
import java.util.*

@Entity(tableName = DbTag.tableName)
data class DbTag(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = idCol,defaultValue = "")
    val id: String,
    @ColumnInfo(name = nameCol,defaultValue = "")
    override val name: String,
    @ColumnInfo(name = isUploadedCol, defaultValue = "0")
    val isUploadedInternal: Int = 0,
) : Tag {
    companion object {
        const val idCol = "id"
        const val nameCol = "name"
        const val tableName = "Tag"
        const val isUploadedCol = "isUploaded"

        fun random(): DbTag {
            val id = UUID.randomUUID().toString()
            return DbTag(
                id = id,
                name = "tag name: ${id.subSequence(0, minOf(5,id.length))}",
                isUploadedInternal = 0
            )
        }
        fun fromTagDoc(td: TagDoc): DbTag {
            return DbTag(id = td.id, name = td.name, isUploadedInternal = 1)
        }
    }

    override val tagId: String
        get() = id
    override val isUploaded: Boolean
        get() = isUploadedInternal > 0

    override fun toDbTag(): DbTag {
        return this
    }
}
