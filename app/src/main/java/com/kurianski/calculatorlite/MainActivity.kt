package com.kurianski.calculatorlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.kurianski.logic.Calculator
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import android.widget.HorizontalScrollView
import android.support.v4.os.HandlerCompat.postDelayed



class MainActivity : AppCompatActivity() {
    private val currentResult:StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tempResult = savedInstanceState?.getString("result") ?: "0"

        currentResult.append(tempResult)
        updateResultDisplay()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("result", currentResult.toString())
        super.onSaveInstanceState(outState)
    }

    fun onButtonPressed(view: View) {
        val button: Button =  view as Button
        if(currentResult.toString() == "0") currentResult.clear()
        currentResult.append(button.text)
        updateResultDisplay()
    }

    fun clearResultDisplay(view: View) {
        currentResult.clear()
        currentResult.append("0")
        updateResultDisplay()
    }

    fun onEqualButtonPressed(view: View) {
        val value = Calculator(currentResult.toString()).result
        currentResult.clear()
        currentResult.append(value)
        updateResultDisplay()
    }

    private fun updateResultDisplay() {
        resultDisplay.text = currentResult.toString()
        scroll.postDelayed({ scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT) }, 100L)
    }
}
