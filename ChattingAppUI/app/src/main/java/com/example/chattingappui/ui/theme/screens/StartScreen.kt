package com.example.chattingappui.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.R
import com.example.chattingappui.ui.theme.component.ButtonComponent
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.chattingappui.ui.theme.component.SpacerWidth
import com.example.chattingappui.ui.theme.navigation.Home


@Composable
fun StartScreen(navHostController: NavHostController) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    )
    {
        Box (
            modifier = Modifier.height(screenHeight / 2)
        ) {
            Image(
                painter = painterResource(id = R.drawable.chat_main),
                contentDescription = "",
                contentScale = ContentScale.FillHeight
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 300.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 40.dp)
            ) {
                Text(
                    text = "Stay Connected with your friends and family",
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                SpacerHeight(20.dp)
                CustomCheckBox()
            }
        }

        ButtonComponent(
            text = "Get Started",
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter)
        ) {
            navHostController.navigate(Home)
        }
    }
}

@Composable
fun CustomCheckBox() {

    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 80.dp,
                        bottomEnd = 80.dp
                    )
                )
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            IconComponentImageVector(
                icon = Icons.Default.Check,
                size = 15.dp,
                tint = Color.Black
            )
        }

        SpacerWidth()

        Text(
            text = "Secure, Private Messaging",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
                color = Color.White
            )
        )
    }

}

@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreview() {
    StartScreen(rememberNavController())
}