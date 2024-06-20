package org.sluman.republic.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DriverCacheEntity::class, RouteCacheEntity::class],
    exportSchema = false,
    version = 1
)
abstract class RepublicDatabase : RoomDatabase() {

    abstract fun driverDao(): DriverDao

    abstract fun routeDao(): RouteDao

    companion object {
        const val DATABASE_NAME: String = "republic_db"
    }
}