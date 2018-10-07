package com.example.guillaume.stones


data class Result(val cards: List<Card>)

data class Card(val name: String, val manaCost: String, val colors: List<String>, val text: String )
