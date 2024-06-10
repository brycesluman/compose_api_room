package org.sluman.republic.data.network

import com.google.gson.annotations.SerializedName

data class DriversAndRoutesNetworkEntity(
    @SerializedName("drivers" ) var drivers : ArrayList<DriverNetworkEntity> = arrayListOf(),
    @SerializedName("routes"  ) var routes  : ArrayList<RouteNetworkEntity>  = arrayListOf()
)

data class DriverNetworkEntity (
    @SerializedName("id"   ) var id   : String,
    @SerializedName("name" ) var name : String
)
data class RouteNetworkEntity (
    @SerializedName("id"   ) var id   : Int,
    @SerializedName("type" ) var type : String,
    @SerializedName("name" ) var name : String
)