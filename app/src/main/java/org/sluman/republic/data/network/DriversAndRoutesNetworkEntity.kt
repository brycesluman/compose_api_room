package org.sluman.republic.data.network

import com.google.gson.annotations.SerializedName

data class DriversAndRoutesNetworkEntity(
    @SerializedName("drivers" ) val drivers : List<DriverNetworkEntity> = listOf(),
    @SerializedName("routes"  ) val routes  : List<RouteNetworkEntity>  = listOf()
)

data class DriverNetworkEntity (
    @SerializedName("id"   ) val id   : String,
    @SerializedName("name" ) val name : String
)
data class RouteNetworkEntity (
    @SerializedName("id"   ) val id   : Int,
    @SerializedName("type" ) val type : String,
    @SerializedName("name" ) val name : String
)