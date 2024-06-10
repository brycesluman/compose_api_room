package org.sluman.republic.data.network

import retrofit2.http.GET

interface ApiService {

    @GET("data")
    suspend fun getDriversAndRoutes(): DriversAndRoutesNetworkEntity
}