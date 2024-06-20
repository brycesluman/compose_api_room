package org.sluman.republic.domain

class GetRouteUseCase(
    private val repository: RouteRepository

) {
    suspend operator fun invoke(driverId: String): RouteDomainEntity? {
        return repository.getRouteForDriver(getRouteId(driverId))
    }

    private suspend fun getRouteId(driverId: String): Int {
        val inCache = repository.getRouteById(driverId.toInt())
        if (inCache != null) {
            return inCache.id
        }

        if (driverId.toInt() % 2 == 0) {
            return repository.getRouteByType("R")[0].id
        }

        if (driverId.toInt() % 5 == 0) {
            return repository.getRouteByType("C")[0].id
        }

        return repository.getRouteByType("I")[0].id
    }
}