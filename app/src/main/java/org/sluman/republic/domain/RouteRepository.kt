package org.sluman.republic.domain

interface RouteRepository {
    suspend fun getRouteForDriver(driverId: String): RouteDomainEntity?
}