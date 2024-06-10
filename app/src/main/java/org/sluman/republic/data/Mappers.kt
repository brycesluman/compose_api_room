package org.sluman.republic.data

import org.sluman.republic.data.network.DriverNetworkEntity
import org.sluman.republic.data.network.RouteNetworkEntity
import org.sluman.republic.database.DriverCacheEntity
import org.sluman.republic.database.RouteCacheEntity
import org.sluman.republic.domain.DriverDomainEntity
import org.sluman.republic.domain.RouteDomainEntity

fun DriverNetworkEntity.toCache(): DriverCacheEntity {
    return DriverCacheEntity(
        id = id,
        name = name
    )
}

fun DriverCacheEntity.toDomain(): DriverDomainEntity {
    return DriverDomainEntity(
        id = id,
        name = name
    )
}

fun RouteNetworkEntity.toCache(): RouteCacheEntity {
    return RouteCacheEntity(
        id = id,
        type = type,
        name = name
    )
}

fun RouteCacheEntity.toDomain(): RouteDomainEntity {
    return RouteDomainEntity(
        id = id,
        type = type,
        name = name
    )
}