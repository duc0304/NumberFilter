package com.example.numberfilter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val textViewError = findViewById<TextView>(R.id.textViewError)
        val listViewResult = findViewById<ListView>(R.id.listViewResult)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            textViewError.text = "" // Clear previous error messages

            if (input.isEmpty()) {
                textViewError.text = "Please enter a number"
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n < 0) {
                textViewError.text = "Please enter a positive integer"
                return@setOnClickListener
            }

            val resultList = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioSquare.isChecked -> getSquareNumbers(n)
                else -> {
                    textViewError.text = "Please select a number type"
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listViewResult.adapter = adapter
        }
    }

    // Function to get even numbers from 0 to n
    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    // Function to get odd numbers from 1 to n
    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    // Function to get square numbers from 0 to n
    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}
