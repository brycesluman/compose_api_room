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
        val drivers =  driverDao.getAll()
        if (drivers?.isEmpty() == true) {
            val response = apiClient.apiService.getDriversAndRoutes()
            println(response.drivers)
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
            return drivers.map {
                it.toDomain()
            }
        }
        return drivers?.map {
            it.toDomain()
        }
    }
}