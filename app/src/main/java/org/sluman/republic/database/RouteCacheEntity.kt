package org.sluman.republic.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes")
data class RouteCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name= "id")
    val id   : Int,
    @ColumnInfo(name= "type")
    val type : String,
    @ColumnInfo(name= "name")
    val name : String
)
