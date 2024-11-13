package com.example.chaosxplorer.views.MapView

import android.graphics.Rect
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.chaosxplorer.R
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


@Composable
fun MapView(
    mapViewModel: MapViewModel,
    modifier: Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    lateinit var mMap: MapView

    val mapState by mapViewModel.uiState.collectAsState()

    LaunchedEffect(lifecycleState) {
        // Do something with your state
        // You may want to use DisposableEffect or other alternatives
        // instead of LaunchedEffect
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {mMap.onResume()}
        }
    }

    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                mMap.onPause()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        modifier = modifier
            .safeDrawingPadding()
            .fillMaxSize(), // Occupy the max size in the Compose UI tree
        factory = { context ->
            // Creates view
            View.inflate(context, R.layout.map_view, null)
        },
        update = { view ->
            mMap = view.findViewById(R.id.map_view)
            mMap.setTileSource(TileSourceFactory.OpenTopo)
            mMap.mapCenter
            mMap.setMultiTouchControls(true)
            mMap.getLocalVisibleRect(Rect())

            val mMyLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(view.context), mMap)
            val controller = mMap.controller

            mMyLocationOverlay.enableMyLocation()
            mMyLocationOverlay.enableFollowLocation()
            mMyLocationOverlay.isDrawAccuracyEnabled = true
            mapState.location?.let {
                mMap.overlays.add(mMyLocationOverlay)
            }


            controller.setZoom(9.5)
            val startPoint = GeoPoint(48.8583, 2.2944);
            controller.setCenter(startPoint);
            val compassOverlay = CompassOverlay(view.context, InternalCompassOrientationProvider(view.context), mMap)
            compassOverlay.enableCompass()
            mMap.overlays.add(compassOverlay)

            val rotationGestureOverlay = RotationGestureOverlay(mMap)
            rotationGestureOverlay.isEnabled
            mMap.setMultiTouchControls(true)
            mMap.overlays.add(rotationGestureOverlay)


        }
    )

}