package org.sluman.republic.data

import org.sluman.republic.domain.RouteDomainEntity

data class RouteUiState(
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val isLoading: Boolean = false,
    val route: RouteDomainEntity? = null
)
