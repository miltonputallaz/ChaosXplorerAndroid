package com.example.chaosxplorer.views.MainView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chaosxplorer.ui.theme.ChaosXplorerTheme



@Composable
fun MainView(
        mainViewModel: MainViewModel,
        modifier: Modifier?) {
        Surface(
                modifier = modifier!!.fillMaxSize().safeDrawingPadding(),
                color = MaterialTheme.colorScheme.background
        ) {
                Text(text = "Hola")
        }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        ChaosXplorerTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                MainView(mainViewModel, null)
        }
}
