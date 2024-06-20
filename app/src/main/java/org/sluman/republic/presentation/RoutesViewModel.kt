package org.sluman.republic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sluman.republic.data.RouteUiState
import org.sluman.republic.domain.GetRouteUseCase
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val getRouteUseCase: GetRouteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RouteUiState())
    val uiState: StateFlow<RouteUiState> = _uiState.asStateFlow()

    fun getRouteDetails(id: String) {
        _uiState.value = uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                val route = getRouteUseCase(id)
                _uiState.value = uiState.value.copy(
                    isError = false,
                    isLoading = false,
                    route = route
                )
            } catch (e: Exception) {
                println("error caught: $e")
                _uiState.value = uiState.value.copy(
                    isError = true,
                    errorMessage = "An error occurred",
                    isLoading = false
                )
            }
        }
    }
}