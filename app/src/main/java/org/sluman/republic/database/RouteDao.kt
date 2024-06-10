package org.sluman.republic.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RouteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(geoEntity: RouteCacheEntity): Long

    @Query("SELECT * FROM routes WHERE id=:id")
    suspend fun get(id: Int): RouteCacheEntity?

    @Query("SELECT * FROM routes WHERE type=:type")
    suspend fun get(type: String): List<RouteCacheEntity>

    @Query("DELETE FROM routes WHERE id=:id")
    suspend fun delete(id: Int)
}