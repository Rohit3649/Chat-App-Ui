package com.example.chattingappui.data

import androidx.annotation.DrawableRes
import com.example.chattingappui.R

data class Status(
    val id: Int,
    @DrawableRes val icon: Int,
    val message: String
)

val statusList = listOf(
    Status(1, R.drawable.taj1, "Wah Taj 1"),
    Status(2, R.drawable.taj2, "Wah Taj 2"),
    Status(3, R.drawable.taj3, "Wah Taj 3"),
    Status(4, R.drawable.taj4, "Wah Taj 4"),
    Status(5, R.drawable.taj5, "Wah Taj 5"),
    Status(6, R.drawable.taj6, "Wah Taj 6"),
)