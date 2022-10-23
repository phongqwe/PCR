package com.qxdzbc.pcr.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qxdzbc.pcr.state.model.Tag

@Entity(tableName =DbTag.tableName)
data class DbTag(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = idCol)
    override val id:String,
    @ColumnInfo(name = nameCol)
    override val name:String
):Tag{
    companion object{
        const val idCol="id"
        const val nameCol ="name"
        const val tableName="Tag"
    }
}
