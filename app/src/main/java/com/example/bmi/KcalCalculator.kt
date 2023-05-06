package com.example.bmi

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class KcalCalculator : AppCompatActivity(), OnItemSelectedListener {
    var selected_gender: String? = null
    var weightEditText: EditText? = null
    var heightEditText: EditText? = null
    var ageEditText: EditText? = null
    var bmrTextView: TextView? = null
    var weightAmount = 0
    var heightAmount = 0
    var ageAmount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kcal_calculator)
        ageEditText = findViewById<View>(R.id.editText_Age) as EditText
        weightEditText = findViewById<View>(R.id.editText_weight) as EditText
        heightEditText = findViewById<View>(R.id.editText_height) as EditText
        bmrTextView = findViewById<View>(R.id.txtView_BMR_result) as TextView
        ageEditText!!.addTextChangedListener(ageEditTextWatcher)
        weightEditText!!.addTextChangedListener(weightEditTextWatcher)
        heightEditText!!.addTextChangedListener(heightEditTextWatcher)
        val spinner_gender = findViewById<View>(R.id.spinner_Gender) as Spinner
        spinner_gender.onItemSelectedListener = this
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_gender.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        if (selected_gender == "Mężczyzna") {
            val bmr =
                10 * weightAmount.toDouble() + 6.25 * heightAmount.toDouble() - 5 * ageAmount + 5
            bmrTextView!!.text = NumberFormat.getNumberInstance()
                .format(bmr) + "\n Tyle kcal dziennie możesz spożyć"
        } else if (selected_gender == "Kobieta") {
            val bmr =
                10 * weightAmount.toDouble() + 6.25 * heightAmount.toDouble() - 5 * ageAmount - 161
            bmrTextView!!.text = NumberFormat.getNumberInstance()
                .format(bmr) + "\n Tyle kcal dziennie możesz spożyć"
        }
    }

    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            weightAmount = try {
                charSequence.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }
            if (weightAmount > 0 && heightAmount > 0 && ageAmount > 0) {
                calculate()
            }
        }

        override fun afterTextChanged(editable: Editable) {}
    }
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            heightAmount = try {
                charSequence.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }
            if (weightAmount > 0 && heightAmount > 0 && ageAmount > 0) {
                calculate()
            }
        }

        override fun afterTextChanged(editable: Editable) {}
    }
    private val ageEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            ageAmount = try {
                charSequence.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }
            if (weightAmount > 0 && heightAmount > 0 && ageAmount > 0) {
                calculate()
            }
        }

        override fun afterTextChanged(editable: Editable) {}
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        selected_gender = adapterView.getItemAtPosition(i).toString()
        if (weightAmount > 0 && heightAmount > 0 && ageAmount > 0) {
            calculate()
        }
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}