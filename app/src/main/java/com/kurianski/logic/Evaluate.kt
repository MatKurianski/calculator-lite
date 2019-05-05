package com.kurianski.logic

import java.util.*

class Evaluate(val inputStack: Stack<Token>) {
    val outputStack: Stack<Int> = Stack()

    private fun applyOP(a: Int, b:Int, op: String): Int {
        when(op) {
            "+" -> return b + a
            "-" -> return b - a
            "*" -> return b * a
            "/" -> return b / a
        }
        return 0
    }

    fun getValue(): Int {
        val iter: Iterator<Token> = inputStack.iterator()
        while(iter.hasNext()) {
            val token = iter.next()
            when(token.tokenType) {
                TokenType.DIGIT -> {
                    outputStack.push(token.value.toInt())
                }
                TokenType.OPERATOR -> {
                    val result = applyOP(outputStack.pop(), outputStack.pop(), token.value)
                    outputStack.push(result)
                }
            }
        }
        return outputStack.reduce {
            sum, element -> sum + element
        }
    }
}