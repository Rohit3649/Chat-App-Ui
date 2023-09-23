package com.example.chattingappui.data



data class Chat(
    val id:Int,
    val message:String,
    val time:String,
    val direction:Boolean //true - chat @left side, false - right side
)

/*val soloChat = Chat(
    1,
    "Hey! How have you been?",
    "12:15 PM",
    true
)*/

val chatList = listOf(
    Chat(
        1,
        "Hey! How are you?",
        "10:10 PM",
        true
    ),
    Chat(
        2,
        "It's been forever since we've seen each other!",
        "10:11 PM",
        true
    ),
    Chat(
        3,
        "I'm doing great! How about you?",
        "10:15 PM",
        false
    ),
    Chat(
        4,
        "I'm doing well, too. I'm so excited to finally be able to get together for dinner.",
        "10:16 PM",
        true
    ),
    Chat(
        5,
        "Me too!",
        "10:16 PM",
        false
    ),
    Chat(
        6,
        "I've missed you.",
        "10:17 PM",
        false
    ),
    Chat(
        7,
        "So, tell me what's new with you.",
        "10:17 PM",
        true
    ),
    Chat(
        8,
        "What have you been up to lately?",
        "10:17 PM",
        true
    ),
    Chat(
        9,
        "Well, I just got a new job",
        "10:20 PM",
        false
    ),
    Chat(
        10,
        "It's really exciting, but it's also been a lot of work. I've been learning a lot, though.",
        "10:21 PM",
        false
    ),
    Chat(
        11,
        "That's awesome! Congratulations on the new job.",
        "10:22 PM",
        true
    ),
    Chat(
        12,
        "Thanks! How about you? What have you been up to?",
        "10:23 PM",
        false
    ),
    Chat(
        13,
        "I've been keeping busy with work and school",
        "10:24 PM",
        true
    ),
    Chat(
        14,
        "I'm also trying to get back into shape, so I've been working out a lot.",
        "10:24 PM",
        true
    ),
    Chat(
        15,
        "That's great! It's important to stay healthy.",
        "10:25 PM",
        false
    ),
    Chat(
        16,
        "So, what should we order for dinner? I'm starving.",
        "10:26 PM",
        true
    ),
    Chat(
        17,
        "I don't know. What do you want?",
        "10:27 PM",
        false
    ),
    Chat(
        18,
        "how about pizza ?",
        "10:28 PM",
        true
    ),
    Chat(
        19,
        "Okay",
        "10:30 PM",
        false
    ),
)