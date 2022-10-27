package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qxdzbc.pcr.firestore.TagDoc
import com.qxdzbc.pcr.state.model.Tag
import java.util.*

@Entity(tableName =DbTag.tableName)
data class DbTag(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = idCol)
    val id: String,
    @ColumnInfo(name = nameCol)
    override val name:String
):Tag{
    companion object{
        const val idCol="id"
        const val nameCol ="name"
        const val tableName="Tag"

        fun random():DbTag{
            val id = UUID.randomUUID().toString()
            return DbTag(
                id= id,
                name = "tag name: $id"
            )
        }
        fun fromTagDoc(td:TagDoc):DbTag{
            return DbTag(id=td.id,name=td.name)
        }
    }

    override val tagId: String
        get() = id

    override fun toDbTag(): DbTag {
        return this
    }
}
