package com.example.guillaume.magic.services

import com.example.guillaume.magic.R
import org.junit.Assert
import org.junit.Test


class ManaInferrerServiceTest {

    @Test
    fun inferManaType_returns_expected_result_with_mana() {
        val result = ManaInferrerService.parseManaCost("{W}{R}")
        Assert.assertEquals(listOf(R.drawable.ic_white_mana, R.drawable.ic_red_mana), result)
    }

    @Test
    fun inferManaType_returns_expected_result_with_number() {
        val result = ManaInferrerService.parseManaCost("3")
        Assert.assertEquals(listOf(3), result)
    }

    @Test
    fun inferManaType_returns_expected_result_with_number_and_mana() {
        val result = ManaInferrerService.parseManaCost("3{W}{R}")
        Assert.assertEquals(listOf(3, R.drawable.ic_white_mana, R.drawable.ic_red_mana), result)
    }
}
