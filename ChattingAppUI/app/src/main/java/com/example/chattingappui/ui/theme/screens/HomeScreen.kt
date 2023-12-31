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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.chattingappui.ui.theme.Gray
import com.example.chattingappui.ui.theme.Gray400
import com.example.chattingappui.ui.theme.Line
import com.example.chattingappui.ui.theme.component.IconComponentDrawable
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.chattingappui.ui.theme.component.SpacerWidth
import com.example.chattingappui.ui.theme.navigation.Chat
import com.example.chattingappui.ui.theme.navigation.Status

@Composable
fun HomeScreen(navHostController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column() {
            HeaderAndStory() {
                navHostController.currentBackStackEntry?.savedStateHandle?.set("data", it)
                navHostController.navigate(Status)
            }
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxSize()
                    .background(
                        Color.White, RoundedCornerShape(
                            topStart = 30.dp, topEnd = 30.dp
                        )
                    )
            ) {
                BottomSheetSwipeUp(
                    modifier = Modifier
                        .align(TopCenter)
                        .padding(top = 15.dp)
                )
                LazyColumn(
                    modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
                ) {
                    items(personList, key = { it.id }) {
                        UserChatEachRow(person = it) {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "data",
                                it
                            )
                            navHostController.navigate(Chat)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetSwipeUp(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(
                Gray400,
                RoundedCornerShape(90.dp)
            )
            .width(90.dp)
            .height(5.dp)
    )
}

@Composable
fun UserChatEachRow(
    modifier: Modifier = Modifier,
    person: Person,
    onChatClick: () -> Unit = {}
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .background(Color.White)
        .clickable { onChatClick() }
        .padding(horizontal = 20.dp, vertical = 7.dp)) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    IconComponentDrawable(icon = person.icon, size = 60.dp)
                    SpacerWidth()
                    Column {
                        Text(
                            text = person.name, style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        SpacerHeight()
                        Text(
                            text = "Okay", style = TextStyle(
                                fontSize = 14.sp,
                                color = Gray
                            )
                        )
                    }

                }
                Text(
                    text = "10:30 PM", style = TextStyle(
                        fontSize = 12.sp,
                        color = Gray
                    )
                )
            }
            SpacerHeight(15.dp)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Line)
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
            .clip(RoundedCornerShape(90.dp))
            .width(90.dp)
            .height(5.dp)
            .background(Gray400)
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
            append("Hello \uD83D\uDC4B")
        }
        append("   ")
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
        horizontalAlignment = CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.White)
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
                    tint = Color.White
                )
            }
        }
        SpacerHeight()
        Text(
            text = "Add Story",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W300,
                color = Color.White
            )
        )
    }
}

@Composable
fun HeaderAndStory(
    onStoryClick: (Person) -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
    ) {
        WelcomeHeader()
        SpacerHeight(20.dp)
        ViewUserStoryLayout() {
            onStoryClick(it)
        }
    }
}

@Composable
fun ViewUserStoryLayout(
    onStoryClick: (Person) -> Unit = {}
) {
    LazyRow() {
        item {
            AddStoryHeader()
            SpacerWidth()
        }
        items(personList, key = {
            it.id
        }) {
            UserStoryLayout(person = it) {
                onStoryClick(it)
            }
        }
    }
}

@Composable
fun UserStoryLayout(
    modifier: Modifier = Modifier,
    person: Person,
    onStoryClick: () -> Unit = {}
) {
    Column(modifier = modifier.padding(end = 10.dp)) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            IconComponentDrawable(icon = person.icon, size = 65.dp) {
                onStoryClick()
            }
        }
        SpacerHeight()
        Text(
            text = person.name,
            style = TextStyle(
                fontSize = 15.sp,
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