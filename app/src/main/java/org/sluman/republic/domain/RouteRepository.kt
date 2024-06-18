package org.sluman.republic.domain

import org.sluman.republic.database.RouteCacheEntity

interface RouteRepository {
    suspend fun getRouteForDriver(routeId: Int): RouteDomainEntity?

    suspend fun getRouteById(routeId: Int): RouteCacheEntity?

    suspend fun getRouteByType(routeType: String): List<RouteCacheEntity>
}