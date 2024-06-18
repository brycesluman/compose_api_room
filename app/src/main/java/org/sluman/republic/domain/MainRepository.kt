package org.sluman.republic.domain

interface MainRepository {

    var sortedAsc: Boolean
    suspend fun fetchDriversAndRoutesReturnDrivers(): List<DriverDomainEntity>?

    suspend fun getAllDrivers(): List<DriverDomainEntity>?
}