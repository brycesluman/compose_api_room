package org.sluman.republic.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driverEntity: DriverCacheEntity): Long

    @Query("SELECT * FROM drivers WHERE name=:name")
    suspend fun get(name: String): DriverCacheEntity?

    @Query("SELECT * FROM drivers")
    suspend fun getAll(): List<DriverCacheEntity>?

    @Query("DELETE FROM drivers WHERE id=:id")
    suspend fun delete(id: Int)
}