package com.example.guillaume.magic.services

import com.example.guillaume.magic.R
import com.example.guillaume.magic.isParsableToInt

class ManaInferrerService {
    companion object {
        fun parseManaCost(raw: String?): List<Int> {
            if (raw != null) {
                return raw.split("([{}])".toRegex())
                        .filter { !it.isEmpty() }
                        .map { inferManaType(it) }
            }
            // TODO refactor
            return listOf()
        }

        private fun inferManaType(manaCost: String): Int {
            return when {
                manaCost.isParsableToInt() -> manaCost.toInt()
                else -> when (manaCost) {
                    "W" -> R.drawable.ic_white_mana
                    "R" -> R.drawable.ic_red_mana
                    "B" -> R.drawable.ic_black_mana
                    "G" -> R.drawable.ic_green_mana
                    "U" -> R.drawable.ic_blue_mana
                    else -> R.drawable.ic_unknow
                }
            }
        }
    }
}