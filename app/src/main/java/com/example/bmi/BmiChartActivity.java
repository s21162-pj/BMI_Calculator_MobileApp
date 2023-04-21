package com.example.bmi;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class BmiChartActivity extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_bmi);

        mChart = findViewById(R.id.chartBmi);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 23.0f));
        entries.add(new Entry(1, 23.3f));
        entries.add(new Entry(2, 23.5f));
        entries.add(new Entry(3, 24.0f));
        entries.add(new Entry(4, 24.3f));
        entries.add(new Entry(5, 23.9f));

        LineDataSet dataSet = new LineDataSet(entries, "Twoje BMI");
        dataSet.setColor(Color.RED);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);

        Description description = new Description();
        description.setText("Zmiany BMI w odstępach czasu");
        mChart.setDescription(description);

        mChart.invalidate();
    }
}