package com.example.android.learningcurve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


public class Analytics extends AppCompatActivity {
    private static final String TAG = "Analytics";
    DatabaseReference databaseReference;
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        // databaseReference = FirebaseDatabase.getInstance().getReference("Analysis");

        mChart = (LineChart) findViewById(R.id.graph);
        //  mChart.setOnChartGestureListener(Analytics.this);
        //   mChart.setOnChartValueSelectedListener(Analytics.this);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);


        List<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(1, 60f));
        yValues.add(new Entry(2, 50f));
        yValues.add(new Entry(3, 55f));
        yValues.add(new Entry(5, 22f));
        yValues.add(new Entry(6, 74f));
        LineDataSet set1 = new LineDataSet(yValues, "Set 1");
        set1.setFillAlpha(110);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        mChart.setData(data);


    }


}

