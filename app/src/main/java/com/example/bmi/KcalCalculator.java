package com.example.bmi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Objects;

public class KcalCalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String selected_gender;
    EditText weightEditText, heightEditText, ageEditText;
    TextView bmrTextView;

    Integer weightAmount = 0;
    Integer heightAmount = 0;
    Integer ageAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kcal_calculator);

        ageEditText = (EditText) findViewById(R.id.editText_Age);
        weightEditText = (EditText) findViewById(R.id.editText_weight);
        heightEditText = (EditText) findViewById(R.id.editText_height);

        bmrTextView = (TextView) findViewById(R.id.txtView_BMR_result);

        ageEditText.addTextChangedListener(ageEditTextWatcher);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        Spinner spinner_gender = (Spinner) findViewById(R.id.spinner_Gender);
        spinner_gender.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapter);

    }


    @SuppressLint("SetTextI18n")
    private void calculate(){
        if (selected_gender.equals("Mężczyzna")) {

            Double bmr = (10 * (double) weightAmount) + (6.25 * (double) heightAmount) - (5 * (int) ageAmount) + 5;

            bmrTextView.setText(NumberFormat.getNumberInstance().format(bmr) + "\n Tyle kcal dziennie możesz spożyć");

        } else if (selected_gender.equals("Kobieta")) {

            Double bmr = (10 * (double) weightAmount) + (6.25 * (double) heightAmount) - (5 * (int) ageAmount) - 161;

            bmrTextView.setText(NumberFormat.getNumberInstance().format(bmr) + "\n Tyle kcal dziennie możesz spożyć");

        }
    }


    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                weightAmount = Integer.parseInt(charSequence.toString());

            }
            catch (NumberFormatException e){
                weightAmount = 0;
            }
            if(weightAmount > 0 && heightAmount > 0 && ageAmount > 0){
                calculate();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                heightAmount = Integer.parseInt(charSequence.toString());

            }
            catch (NumberFormatException e){
                heightAmount = 0;
            }
            if(weightAmount > 0 && heightAmount > 0 && ageAmount > 0){
                calculate();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                ageAmount = Integer.parseInt(charSequence.toString());

            }
            catch (NumberFormatException e){
                ageAmount = 0;
            }
            if(weightAmount > 0 && heightAmount > 0 && ageAmount > 0){
                calculate();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected_gender = adapterView.getItemAtPosition(i).toString();
        if(weightAmount > 0 && heightAmount > 0 && ageAmount > 0) {
            calculate();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}