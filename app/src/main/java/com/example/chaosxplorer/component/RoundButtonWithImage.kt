package com.example.chaosxplorer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RoundButtonWithImage(imageResource: Int, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape, // Forma redonda
        contentPadding = PaddingValues(0.dp), // Sin padding interno
        modifier = modifier.width(64.dp).height(64.dp) // Tamaño del botón, ajusta a tus necesidades
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Botón con imagen",
            contentScale = ContentScale.Crop, // Recorta la imagen para que se adapte al botón
            modifier = Modifier.clip(CircleShape) // Corta la imagen para que siga la forma circular
        )
    }
}
