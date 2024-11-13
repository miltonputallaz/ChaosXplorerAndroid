package com.example.chaosxplorer

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chaosxplorer.ui.theme.ChaosXplorerTheme
import com.example.chaosxplorer.views.AlertDialogClass
import com.example.chaosxplorer.views.MainView.MainView
import com.example.chaosxplorer.views.MainView.MainViewModel
import com.example.chaosxplorer.views.MapView.MapView
import com.example.chaosxplorer.views.MapView.MapViewModel
import com.example.chaosxplorer.views.NavigationScreens
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        enableEdgeToEdge()
        setContent {
            ChaosXplorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavigationScreens.MainView
                    ) {
                        composable<NavigationScreens.MainView> {
                            val viewModel = hiltViewModel<MainViewModel>()
                            MainView(viewModel, Modifier.padding(innerPadding)) {

                                    navController.navigate(NavigationScreens.MapView)

                            }
                        }
                        composable<NavigationScreens.MapView> {
                            val viewModel = hiltViewModel<MapViewModel>()
                            MapView(viewModel, Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}