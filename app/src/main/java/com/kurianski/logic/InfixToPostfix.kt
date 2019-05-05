package com.kurianski.logic

import java.util.*

class InfixToPostfix(input: String) {
    private val input: CharArray = input.toCharArray()
    private val operatorStack: Stack<Token> = Stack()

    private var current = 0

    var outputStack: Stack<Token> = Stack()

    init {
        convert()
    }

    private fun isAtEnd(): Boolean {
        return current >= input.size
    }

    private fun next(): Char {
        return input[current++]
    }

    private fun isOperator(value: Char): Boolean {
        when(value) {
            '+', '-', '/', '*' -> return true
        }
        return false
    }

    private fun addToOutput(token: Token) {
        this.outputStack.push(token)
    }

    private fun parseDigit(): String {
        val peek: () -> Char = { input[current] }
        val isDigit = {i: Char ->  i in '0'..'9'}

        val start = current-1

        while(!isAtEnd() && isDigit(peek())) next()
        return input.slice(start until current).joinToString(separator = "") {it.toString()}
    }

    private fun convert() {
        while(!isAtEnd()) {
            val char = next()
            if(char == ' ') continue

            if (isOperator(char)) {
                val token = Token(char, TokenType.OPERATOR)
                while(!operatorStack.isEmpty() && (operatorStack.lastElement().precedent >= token.precedent)) {
                    addToOutput(operatorStack.pop())
                }
                operatorStack.push(token)
            } else if(char == '(') {
                operatorStack.push(Token(char, TokenType.LEFT_PAREN))
            } else if(char == ')') {
                while(!operatorStack.isEmpty() && operatorStack.lastElement().tokenType != TokenType.LEFT_PAREN) {
                    addToOutput(operatorStack.pop())
                }
                operatorStack.pop()
            } else {
                val parsedDigit = parseDigit()
                addToOutput(Token(parsedDigit, TokenType.DIGIT))
            }
        }

        while(!operatorStack.isEmpty()) {
            addToOutput(operatorStack.pop())
        }
    }

    override fun toString(): String {
        val iter: Iterator<Token> = outputStack.iterator()
        var outputString = ""

        if(iter.hasNext()) outputString = iter.next().toString()
        else return outputString

        while(iter.hasNext()) {
            outputString += " " + iter.next().toString()
        }
        return outputString
    }
}