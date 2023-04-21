package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    TextView txtView_question_number, txtView_question;
    Button ans1, ans2, ans3, ans4, res;
    String question, good_answer;
    List<Integer> numberList;
    String[] answers;
    String[] questions_array;
    String[] answers_array;
    String[] good_answer_array;
    Map<String, String[]> questions_map;
    int score = 0;
    int totalQuestionsNumber;
    int currentQuestionsNumber = 0;
    int random_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questions_array = getResources().getStringArray(R.array.questions);
        answers_array = getResources().getStringArray(R.array.answers);
        good_answer_array = getResources().getStringArray(R.array.good_answer);

        totalQuestionsNumber = questions_array.length;

        txtView_question_number = findViewById(R.id.txtView_question_number);
        txtView_question = findViewById(R.id.txtView_question);
        ans1 = findViewById(R.id.button_answer1);
        ans2 = findViewById(R.id.button_answer2);
        ans3 = findViewById(R.id.button_answer3);
        ans4 = findViewById(R.id.button_answer4);
        res = findViewById(R.id.button_reset);

        currentQuestionsNumber = 0;

        questions_map = new HashMap<>();
        for (int i = 0; i < totalQuestionsNumber; i++) {
            question = questions_array[i];
            answers = answers_array[i].split(",");
            good_answer = good_answer_array[i];
            questions_map.put(question, new String[]{TextUtils.join("\n", answers), good_answer});
        }

        for (Button button : Arrays.asList(ans1, ans2, ans3, ans4)) {
            button.setOnClickListener(v -> {
                if (Objects.equals(questions_map.get(questions_array[random_number])[1], button.getText().toString())){
                    score += 1;
                }
                questions_map.remove(questions_array[random_number]);
                next_question();
            });
        }

        res.setOnClickListener(v -> reset_quiz());

        next_question();

    }

    private void reset_quiz(){
        currentQuestionsNumber = 0;
        score = 0;

        questions_map = new HashMap<>();
        for (int i = 0; i < totalQuestionsNumber; i++) {
            question = questions_array[i];
            answers = answers_array[i].split(",");
            good_answer = good_answer_array[i];
            questions_map.put(question, new String[]{TextUtils.join("\n", answers), good_answer});
        }

        next_question();
    }


    private void next_question(){
        if ( currentQuestionsNumber >= totalQuestionsNumber) {
            finishQuiz();
        }
        else {
            for (int j = 0; j < 4; j++) {
                Integer[] numbers = {0, 1, 2, 3}; // Replace with your desired numbers
                numberList = Arrays.asList(numbers);
                Collections.shuffle(numberList, new Random());
            }
            Random rand = new Random();


            do{
                random_number = rand.nextInt(totalQuestionsNumber);

            } while (!questions_map.containsKey(questions_array[random_number]));



            currentQuestionsNumber += 1;
            txtView_question_number.setText(MessageFormat.format("{0}/{1}", currentQuestionsNumber, totalQuestionsNumber));

            txtView_question.setText(questions_array[random_number]);
            answers = questions_map.get(questions_array[random_number])[0].split("\n");
            ans1.setText(answers[numberList.get(0)]);
            ans2.setText(answers[numberList.get(1)]);
            ans3.setText(answers[numberList.get(2)]);
            ans4.setText(answers[numberList.get(3)]);

        }

    }
    void finishQuiz(){

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.quiz_finish_title))
                .setMessage(MessageFormat.format(getString(R.string.quiz_finish_score), score, totalQuestionsNumber))
                .setPositiveButton(getString(R.string.quiz_finish_button), ((dialogInterface, i) -> reset_quiz()))
                .setCancelable(false)
                .show();
    }

}