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

    private var sortedAsc = true
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
        return getSortedDrivers()
    }

    override suspend fun getSortedDrivers(): List<DriverDomainEntity>? {
        sortedAsc = !sortedAsc
        val driversList = driverDao.getAll()?.map {
            it.toDomain()
        }
        return driversList?.let { drivers ->
            if (sortedAsc) {
                drivers.sortedWith(compareBy { it.name.split(" ")[1] })
            } else {
                drivers.sortedWith(compareBy { it.name.split(" ")[1] }).asReversed()
            }
        }
    }
}