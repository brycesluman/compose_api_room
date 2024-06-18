package org.sluman.republic.domain

interface MainRepository {
    suspend fun fetchDriversAndRoutesReturnDrivers(): List<DriverDomainEntity>?

    suspend fun getSortedDrivers(): List<DriverDomainEntity>?
}