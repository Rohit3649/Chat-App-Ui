package com.example.chattingappui.data


import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.chattingappui.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int = 0,
    val name: String = "",
    @DrawableRes val icon: Int = R.drawable.rohit1_circle
) : Parcelable

/*val soloPerson = Person(
    1,
    "Pranav",
    R.drawable.person_1
)*/

val imageList = listOf(
    R.drawable.gaj1_circle,
    R.drawable.saman1_circle,
    R.drawable.doc1_circle,
    R.drawable.baba1_circle
)

val personList = listOf(
    Person(
        1,
        "Neeraj",
        R.drawable.neeraj1_circle
    ),
    Person(
        2,
        "Ranjeet",
        R.drawable.saman1_circle
    ),
    Person(
        3,
        "Abhishek",
        R.drawable.gaj1_circle
    ),
    Person(
        4,
        "Vipul",
        R.drawable.doc1_circle
    ),
    Person(
        5,
        "Vijay",
        R.drawable.baba1_circle
    ),
    Person(
        6,
        "Abhishek",
        R.drawable.gaj1_circle
    ),
    Person(
        7,
        "Ranjeet",
        R.drawable.saman1_circle
    ),
    Person(
        8,
        "Vijay",
        R.drawable.baba1_circle
    ),
    Person(
        9,
        "Vipul",
        R.drawable.doc1_circle
    ),
    Person(
        10,
        "Neeraj",
        R.drawable.neeraj1_circle
    ),
)