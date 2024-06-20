package org.sluman.republic.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.sluman.republic.R
import org.sluman.republic.data.RouteUiState

@Composable
fun RoutesScreen(
    modifier: Modifier,
    state: State<RouteUiState>
) {
    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.value.route?.let { route ->
                Text(
                    text = route.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }
        }

        if (state.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
        if (state.value.isError) {
            state.value.errorMessage?.let {
                ErrorMessage(
                    message = stringResource(R.string.error),
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }
        }
    }
}
