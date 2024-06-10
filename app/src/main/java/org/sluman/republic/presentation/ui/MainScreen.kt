package org.sluman.republic.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.sluman.republic.R
import org.sluman.republic.presentation.MainViewModel
import org.sluman.republic.presentation.RoutesViewModel

@Composable
fun MainScreenRoot(
    navController: NavHostController = rememberNavController()
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: DriversList::class

    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarTitle = rememberSaveable { (mutableStateOf("")) }
    topBarState.value = "class $currentScreen" == DriversList::class.toString()

    Scaffold(
        topBar = {
            AppBar(
                navigateUp = { navController.navigateUp() },
                sortDrivers = { mainViewModel.sortList() },
                topBarState = topBarState,
                topBarTitle = topBarTitle
            )
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DriversList,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            composable<DriversList> {
                DriversScreen(
                    onItemClicked = {id, name ->
                        topBarTitle.value = name
                        navController.navigate(
                            RouteList(
                                driverId = id
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize(),
                    state = mainViewModel.uiState.collectAsState()
                )
            }
            composable<RouteList> { entry ->
                val args = entry.toRoute<RouteList>()
                val routesViewModel: RoutesViewModel = hiltViewModel()
                LaunchedEffect(args.driverId) {
                    routesViewModel.getRouteDetails(args.driverId)
                }
                RoutesScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = routesViewModel.uiState.collectAsState()
                )
            }
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navigateUp: () -> Unit,
    sortDrivers: () -> Unit,
    modifier: Modifier = Modifier,
    topBarState: MutableState<Boolean>,
    topBarTitle: MutableState<String>
) {
    TopAppBar(
        title = {
            Row() {
                if (topBarState.value) {
                    Image(
                        painter = painterResource(R.drawable.republic_logo),
                        contentDescription = "logo",
                        modifier = Modifier.size(72.dp)
                    )
                } else {
                    Text(text = topBarTitle.value)
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier,
        navigationIcon = {
            if (!topBarState.value) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            if (topBarState.value) {
                IconButton(onClick = { sortDrivers() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort_by_alpha),
                        contentDescription = "Sort",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
        }
    )
}
@Composable
fun ErrorMessage(message: String, modifier: Modifier) {
    Text(text = message,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier.padding(16.dp)
    )
}

@Serializable
object DriversList

@Serializable
data class RouteList(
    val driverId: String
)