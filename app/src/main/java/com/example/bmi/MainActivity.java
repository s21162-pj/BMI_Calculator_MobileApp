package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //add weight/height and default values
    private double weight = 0.0;
    private double height = 0.0;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView bmiTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightTextView = (TextView) findViewById(R.id.weightTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        bmiTextView = (TextView) findViewById(R.id.bmiTextView);
        bmiTextView.setText(Double.toString(0.0));

        EditText weightEditText =
                (EditText) findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        EditText heightEditText =
                (EditText) findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(heightEditTextWatcher);
    }

    //calculate BMI
    private void calculate() {
        double bmi = weight / (height * height);
        String category;
        if (bmi < 16.0) {
            category = getString(R.string.wyglodzenie);
            bmiTextView.setBackgroundColor(Color.parseColor("RED"));
        } else if (bmi < 17.0) {
            category = getString(R.string.wychudzenie);
            bmiTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
        } else if (bmi < 18.5) {
            category = getString(R.string.niedowaga);
            bmiTextView.setBackgroundColor(Color.parseColor("YELLOW"));
        } else if (bmi < 25.0) {
            category = getString(R.string.bmiok);
            bmiTextView.setBackgroundColor(Color.parseColor("GREEN"));
        } else if (bmi < 30.0) {
            category = getString(R.string.nadwaga);
            bmiTextView.setBackgroundColor(Color.parseColor("YELLOW"));
        } else if (bmi < 35.0) {
            category = getString(R.string.ot1);
            bmiTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
        } else if (bmi < 40.0) {
            category = getString(R.string.ot2);
            bmiTextView.setBackgroundColor(Color.parseColor("RED"));
        } else {
            category = getString(R.string.ot3);
            bmiTextView.setBackgroundColor(Color.parseColor("RED"));
        }
        bmiTextView.setText(Double.toString(bmi));
        TextView categoryTextView = findViewById(R.id.categoryTextView);
        categoryTextView.setText(category);

    }

    //configure weight listener
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            try {
                weight = Double.parseDouble(s.toString()) / 100.0;
                weightTextView.setText(Double.toString(weight));
            } catch (NumberFormatException e) {
                weight = 0.0;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    //configure height listener
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                height = Double.parseDouble(s.toString()) / 100.0;
                heightTextView.setText(Double.toString(height));
            } catch (NumberFormatException e) {
                height = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            //show bmi after add height
            calculate();
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };
}
