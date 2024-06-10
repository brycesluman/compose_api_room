package org.sluman.republic.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class DriverCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name= "id")
    var id   : String,
    @ColumnInfo(name= "name")
    var name : String
)