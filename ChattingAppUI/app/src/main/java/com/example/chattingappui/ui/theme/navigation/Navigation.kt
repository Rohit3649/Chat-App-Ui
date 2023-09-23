package com.example.chattingappui.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.ui.theme.screens.ChatScreen
import com.example.chattingappui.ui.theme.screens.HomeScreen
import com.example.chattingappui.ui.theme.screens.StartScreen
import com.example.chattingappui.ui.theme.screens.StatusScreen


@Composable
fun Navigation() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Start) {
        composable(route = Start) {
            StartScreen(navHostController)
        }
        composable(route = Home) {
            HomeScreen(navHostController)
        }
        composable(route = Chat) {
            ChatScreen(navHostController)
        }
        composable(route = Status) {
            StatusScreen(navHostController)
        }
    }
}

const val Start = "StartScreen"
const val Home = "HomeScreen"
const val Chat = "ChatScreen"
const val Status = "StatusScreen"