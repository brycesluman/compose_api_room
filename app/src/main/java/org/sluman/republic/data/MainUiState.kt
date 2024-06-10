package org.sluman.republic.data

import org.sluman.republic.domain.DriverDomainEntity

data class MainUiState(
    val sortedAsc: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String? = "",
    val isLoading: Boolean = false,
    val drivers: List<DriverDomainEntity>? = null
)
