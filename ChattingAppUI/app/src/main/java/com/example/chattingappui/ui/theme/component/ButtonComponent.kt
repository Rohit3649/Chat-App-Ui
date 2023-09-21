package com.example.chattingappui.ui.theme.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    background: Color = Color.White,
    foreground: Color = Color.Black,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(0.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = background,
        contentColor = foreground,
    ),
    shape: Shape = RoundedCornerShape(80.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = elevation,
        colors = colors,
        shape = shape
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = foreground
            )
        )
    }
}