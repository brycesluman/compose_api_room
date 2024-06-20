package org.sluman.republic.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import org.sluman.republic.data.MainUiState

@Composable
fun DriversScreen(
    modifier: Modifier,
    onItemClicked: (id: String, name: String) -> Unit,
    state: State<MainUiState>
) {

    Box(modifier = modifier
        .fillMaxHeight()
        .padding(16.dp)) {

        LazyColumn {
            state.value.drivers?.let {
                items(it) { item ->
                    Row(modifier = Modifier
                        .clickable {
                            onItemClicked(item.id, item.name)
                        }
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp)) {
                        Text(
                            text = item.id,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = item.name,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    HorizontalDivider(thickness = .5.dp)
                }
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
