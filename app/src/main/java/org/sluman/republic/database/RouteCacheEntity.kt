package org.sluman.republic.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes")
data class RouteCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name= "id")
    var id   : Int,
    @ColumnInfo(name= "type")
    var type : String,
    @ColumnInfo(name= "name")
    var name : String
)
