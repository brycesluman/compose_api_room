package org.sluman.republic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.sluman.republic.presentation.ui.MainScreenRoot
import org.sluman.republic.presentation.ui.theme.RepublicTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RepublicTheme {
                MainScreenRoot()
            }
        }
    }
}
