package com.example.chattingappui.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.data.Person
import com.example.chattingappui.data.personList
import com.example.chattingappui.data.soloPerson
import com.example.chattingappui.ui.theme.component.IconComponentDrawable
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.chattingappui.ui.theme.component.SpacerWidth


@Composable
fun HomeScreen(navHostController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column() {
            HeaderAndStory()
            CreateChatWindow()
        }
    }
}

@Composable
fun UserChatEachRow(
    modifier: Modifier = Modifier,
    person: Person,
    onClick:() -> Unit = {}
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .background(Color.White)
        .clickable { onClick() }
        .padding(horizontal = 20.dp, vertical = 5.dp)) {
        Column {
            Row {

            }
        }
    }
}

@Composable
fun CreateChatWindow() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                )
            )
            .background(Color.White)
    ) {
        BottomSheetSwipe(
            modifier = Modifier
                .align(TopCenter)
                .padding(top = 15.dp)
        )
    }
}

@Composable
fun BottomSheetSwipe(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(90.dp))
            .width(90.dp)
            .height(5.dp)
            .background(Color.Green)
    ) {

    }
}

@Composable
fun WelcomeHeader() {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W300,
                color = Color.White
            )
        ) {
            append("Welcome Back")
        }
        SpacerWidth()
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.White
            )
        ) {
            append("Rohit!")
        }
    }

    Text(text = text)
}

@Composable
fun AddStoryHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.Yellow)
                .border(2.dp, Color.Red, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                IconComponentImageVector(
                    icon = Icons.Default.Add,
                    size = 15.dp,
                    tint = Color.Yellow
                )
            }
        }
        SpacerHeight()
        Text(
            text = "Add Story",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W300,
                color = Color.White
            )
        )
    }
}

@Composable
fun HeaderAndStory() {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
    ) {
        WelcomeHeader()
        SpacerHeight()
        ViewUserStoryLayout()
    }
}

@Composable
fun ViewUserStoryLayout() {
    LazyRow() {
        item {
            AddStoryHeader()
            SpacerWidth()
        }
        items(personList, key = { it.id }) {
            UserStoryLayout(person = it)
        }
    }
}

@Composable
fun UserStoryLayout(
    modifier: Modifier = Modifier,
    person: Person
) {
    Column(modifier = Modifier.padding(end = 10.dp)) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            IconComponentDrawable(icon = person.icon, size = 65.dp)
        }
        SpacerHeight()
        Text(
            text = person.name,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W300,
                color = Color.White
            ),
            modifier = Modifier.align(CenterHorizontally)
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
fun ShowPreviewHomeScreen() {
    HomeScreen(rememberNavController())
}