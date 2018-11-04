package com.example.guillaume.magic

fun String.isParsableToInt(): Boolean {
    val v = this.toIntOrNull()
    return when(v) {
        null -> false
        else -> true
    }
}