package com.kurianski.logic

data class Token(val value: String, val tokenType: TokenType) {
    constructor(value: Char, tokenType: TokenType) : this(value.toString(), tokenType)

    val precedent: Int = when(value) {
        "*", "/" ->  3
        "+", "-" ->  2
        else -> 1
    }

    override fun toString(): String {
        return value
    }
}