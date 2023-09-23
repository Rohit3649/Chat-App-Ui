package com.example.chattingappui.ui.theme.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.R
import com.example.chattingappui.data.Chat
import com.example.chattingappui.data.Person
import com.example.chattingappui.data.chatList
import com.example.chattingappui.ui.theme.Gray
import com.example.chattingappui.ui.theme.Gray400
import com.example.chattingappui.ui.theme.LightRed
import com.example.chattingappui.ui.theme.LightYellow
import com.example.chattingappui.ui.theme.component.IconComponentDrawable
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.chattingappui.ui.theme.component.SpacerWidth


@Composable
fun ChatScreen(navHostController: NavHostController) {

    var message by remember { mutableStateOf("") }

    val person =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Person>("data") ?: Person()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        )
        {

            UserEachRow(
                person = person,
                modifier = Modifier.padding(
                    start = 20.dp,
                    top = 30.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .padding(top = 10.dp)
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 15.dp,
                            end = 15.dp,
                            top = 25.dp,
                            bottom = 75.dp
                        )
                ) {
                    items(chatList, key = {
                        it.id
                    }) {
                        ChatRow(chat = it, person = person)
                    }
                }

                TextFieldComponent(
                    text = message,
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    message = it
                }
            }
        }
    }
}

@Composable
fun ChatRow(
    chat: Chat,
    person: Person
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (chat.direction) Alignment.Start else Alignment.End
    ) {
        Row {
            if (chat.direction) {
                IconComponentDrawable(icon = R.drawable.rohit1_circle, size = 33.dp)
                SpacerWidth(3.dp)
            }
            Box(
                modifier = Modifier
                    .background(
                        color = if (chat.direction) LightRed else LightYellow,
                        shape = RoundedCornerShape(100.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = chat.message,
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(
                        horizontal = 15.dp,
                        vertical = 8.dp
                    )
                )
            }
            if (!chat.direction) {
                SpacerWidth(3.dp)
                IconComponentDrawable(icon = person.icon, size = 33.dp)
            }
        }

        Text(
            text = chat.time,
            style = TextStyle(
                fontSize = 12.sp,
                color = Gray
            ),
            modifier = Modifier.padding(
                horizontal = 15.dp,
                vertical = 8.dp
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(160.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray400,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Type Message",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
        },
        leadingIcon = {
            IconButtonComponentImageVector(Icons.Default.Add)
        },
        trailingIcon = {
            IconButtonComponentDrawable(R.drawable.mic)
        }
    )
}

@Composable
fun IconButtonComponentDrawable(
    @DrawableRes icon: Int
) {
    Box(
        modifier = Modifier
            .background(Color.Yellow, CircleShape)
            .size(33.dp),
        contentAlignment = Alignment.Center
    ) {
        IconComponentDrawable(icon = icon, size = 15.dp, tint = Color.Black)
    }
}

@Composable
fun IconButtonComponentImageVector(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .background(Color.Yellow, CircleShape)
            .size(33.dp),
        contentAlignment = Alignment.Center
    ) {
        IconComponentImageVector(icon = icon, size = 15.dp, tint = Color.Black)
    }
}

@Composable
fun UserEachRow(
    modifier: Modifier = Modifier,
    person: Person
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box(modifier = Modifier
                .align(CenterVertically)
                .padding(end = 5.dp)) {
                IconComponentImageVector(
                    icon = Icons.Default.ArrowBack,
                    size = 24.dp,
                    tint = Color.White,
                )
            }

            IconComponentDrawable(icon = person.icon, size = 50.dp)
            SpacerWidth(8.dp)
            Column {
                Text(
                    text = person.name, style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                SpacerHeight()
                Text(
                    text = "Online",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
            }
        }
        IconComponentImageVector(icon = Icons.Default.MoreVert, size = 24.dp, tint = Color.White)
    }
}

@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreviewChat() {
    ChatScreen(rememberNavController())
}