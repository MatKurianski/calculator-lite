package com.kurianski.logic

class Calculator(infixExpression: String) {
    val result: Int

    init {
        val postFixExpression = InfixToPostfix(infixExpression).outputStack
        result = Evaluate(postFixExpression).getValue()
    }
}