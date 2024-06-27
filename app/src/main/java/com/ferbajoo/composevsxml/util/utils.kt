package com.ferbajoo.composevsxml.util

import kotlin.random.Random

val emojis =
    listOf(
        "\uD83D\uDE01",
        "\uD83D\uDE0B",
        "\uD83D\uDE13",
        "\uD83D\uDE23",
        "\uD83D\uDE37",
        "\uD83D\uDE35",
        "\uD83D\uDE49",
        "\uD83D\uDE4A",
        "\uD83D\uDE4E",
        "\uD83D\uDE30",
    )

fun getEmojiRandom(): String {
    return emojis[Random.nextInt(0, emojis.size - 1)]
}