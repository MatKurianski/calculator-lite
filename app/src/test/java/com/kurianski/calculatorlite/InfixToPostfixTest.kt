package com.kurianski.calculatorlite

import com.kurianski.logic.InfixToPostfix
import org.junit.Assert.assertEquals
import org.junit.Test

class InfixToPostfixTest {

    @Test
    fun test1() {
        val postFixExpression = InfixToPostfix("(3 * 9) / 3 - 5").toString()
        assertEquals("3 9 * 3 / 5 -", postFixExpression)
    }

    @Test
    fun test2() {
        val postFixExpression = InfixToPostfix("2444 - 52 * 330 + 30000 - 51 * 18").toString()
        assertEquals("2444 52 330 * - 30000 + 51 18 * -", postFixExpression)
    }
}
