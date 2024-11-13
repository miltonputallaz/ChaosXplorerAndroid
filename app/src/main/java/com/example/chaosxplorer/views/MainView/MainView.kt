package com.example.chaosxplorer.views.MainView

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chaosxplorer.ui.theme.ChaosXplorerTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.chaosxplorer.R
import com.example.chaosxplorer.component.AlertWithMessage
import com.example.chaosxplorer.component.RoundButtonWithImage
import com.example.chaosxplorer.views.AlertDialogClass
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainView(
        mainViewModel: MainViewModel,
        modifier: Modifier?,
        goToMapView: () -> Unit) {
        val mainState by mainViewModel.uiState.collectAsState()
        val locationPermissionState = rememberMultiplePermissionsState( listOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION))
        var triedToGoToMapView by remember { mutableStateOf(false) }


        ConstraintLayout(
                modifier = Modifier.safeDrawingPadding().fillMaxSize()
        ) {
                val (bottomBar, worldButton) = createRefs()


                BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.constrainAs(bottomBar){
                                bottom.linkTo(parent.bottom)
                        }
                ) {
                        Text(
                                modifier = Modifier
                                        .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "Bottom app bar",
                        )
                }

                RoundButtonWithImage(imageResource = R.drawable.world, Modifier.constrainAs(worldButton){
                        bottom.linkTo(bottomBar.top)
                        top.linkTo(bottomBar.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                })  {
                        triedToGoToMapView = true
                        if (locationPermissionState.allPermissionsGranted) {
                                goToMapView()
                        }
                }


                if (triedToGoToMapView && locationPermissionState.allPermissionsGranted) {
                        triedToGoToMapView = false
                        goToMapView()
                }

                if (triedToGoToMapView && !locationPermissionState.allPermissionsGranted) {
                        if (locationPermissionState.shouldShowRationale) {
                                mainViewModel.showDialog(AlertDialogClass("Location permission", "Please grant location permission to continue", {locationPermissionState.launchMultiplePermissionRequest()},{locationPermissionState.launchMultiplePermissionRequest()},{locationPermissionState.launchMultiplePermissionRequest()}))
                        } else {
                                locationPermissionState.launchMultiplePermissionRequest()
                        }
                }

                mainState.alertDialogClass?.let {
                        AlertWithMessage(it)
                }

        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        ChaosXplorerTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                MainView(mainViewModel, Modifier, {})
        }
}
