package org.sluman.republic.data

import org.sluman.republic.data.network.ApiClient
import org.sluman.republic.database.DriverDao
import org.sluman.republic.database.RouteDao
import org.sluman.republic.domain.DriverDomainEntity
import org.sluman.republic.domain.MainRepository

class MainRepositoryImpl(private val apiClient: ApiClient,
                         private val driverDao: DriverDao,
                         private val routeDao: RouteDao
    ): MainRepository {
    override suspend fun fetchDriversAndRoutesReturnDrivers(): List<DriverDomainEntity>? {
        val drivers = driverDao.getAll()
        if (drivers?.size == 0) {
            val response = apiClient.apiService.getDriversAndRoutes()
            response.drivers.map {
                it.toCache().let { item ->
                    driverDao.insert(item)
                }
            }
            response.routes.map {
                it.toCache().let { item ->
                    routeDao.insert(item)
                }
            }
        }
        return driverDao.getAll()?.map {
            it.toDomain()
        }
    }
}