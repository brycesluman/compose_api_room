package org.sluman.republic.data

import org.sluman.republic.database.RouteCacheEntity
import org.sluman.republic.database.RouteDao
import org.sluman.republic.domain.RouteDomainEntity
import org.sluman.republic.domain.RouteRepository

class RouteRepositoryImpl(
    private val dao: RouteDao
) : RouteRepository {
    override suspend fun getRouteForDriver(routeId: Int): RouteDomainEntity? {
        return dao.get(routeId)?.toDomain()
    }

    override suspend fun getRouteById(routeId: Int): RouteCacheEntity? {
        return dao.get(routeId)
    }
    override suspend fun getRouteByType(routeType: String): List<RouteCacheEntity> {
        return dao.get(routeType)
    }
}