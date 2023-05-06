package com.example.bmi

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //add weight/height and default values
    private var weight = 0.0
    private var height = 0.0
    private var heightTextView: TextView? = null
    private var weightTextView: TextView? = null
    private var bmiTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weightTextView = findViewById<View>(R.id.weightTextView) as TextView
        heightTextView = findViewById<View>(R.id.heightTextView) as TextView
        bmiTextView = findViewById<View>(R.id.bmiTextView) as TextView
        bmiTextView!!.text = java.lang.Double.toString(0.0)
        val weightEditText = findViewById<View>(R.id.weightEditText) as EditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText = findViewById<View>(R.id.heightEditText) as EditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
    }

    //calculate BMI
    private fun calculate() {
        val bmi = weight / (height * height)
        val category: String
        if (bmi < 16.0) {
            category = getString(R.string.wyglodzenie)
            bmiTextView!!.setBackgroundColor(Color.parseColor("RED"))
        } else if (bmi < 17.0) {
            category = getString(R.string.wychudzenie)
            bmiTextView!!.setBackgroundColor(Color.parseColor("#FFBF00"))
        } else if (bmi < 18.5) {
            category = getString(R.string.niedowaga)
            bmiTextView!!.setBackgroundColor(Color.parseColor("YELLOW"))
        } else if (bmi < 25.0) {
            category = getString(R.string.bmiok)
            bmiTextView!!.setBackgroundColor(Color.parseColor("GREEN"))
        } else if (bmi < 30.0) {
            category = getString(R.string.nadwaga)
            bmiTextView!!.setBackgroundColor(Color.parseColor("YELLOW"))
        } else if (bmi < 35.0) {
            category = getString(R.string.ot1)
            bmiTextView!!.setBackgroundColor(Color.parseColor("#FFBF00"))
        } else if (bmi < 40.0) {
            category = getString(R.string.ot2)
            bmiTextView!!.setBackgroundColor(Color.parseColor("RED"))
        } else {
            category = getString(R.string.ot3)
            bmiTextView!!.setBackgroundColor(Color.parseColor("RED"))
        }
        bmiTextView!!.text = java.lang.Double.toString(bmi)
        val categoryTextView = findViewById<TextView>(R.id.categoryTextView)
        categoryTextView.text = category
    }

    //configure weight listener
    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try {
                weight = s.toString().toDouble() / 100.0
                weightTextView!!.text = java.lang.Double.toString(weight)
            } catch (e: NumberFormatException) {
                weight = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    //configure height listener
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try {
                height = s.toString().toDouble() / 100.0
                heightTextView!!.text = java.lang.Double.toString(height)
            } catch (e: NumberFormatException) {
                height = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {
            //show bmi after add height
            calculate()
        }

        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }
}