package org.sluman.republic.data

import org.sluman.republic.database.RouteDao
import org.sluman.republic.domain.RouteDomainEntity
import org.sluman.republic.domain.RouteRepository

class RouteRepositoryImpl(
    private val dao: RouteDao
): RouteRepository {
    override suspend fun getRouteForDriver(driverId: String): RouteDomainEntity? {
        return dao.get(getRouteId(driverId))?.toDomain()
    }

    private suspend fun getRouteId(driverId: String): Int {
        val inCache = dao.get(driverId.toInt())
        if (inCache!= null) {
            return inCache.id
        }

        if (driverId.toInt()%2 == 0) {
            return dao.get("R")[0].id
        }

        if (driverId.toInt()%5 == 0) {
            return dao.get("C")[0].id
        }

        return dao.get("I")[0].id
    }
}