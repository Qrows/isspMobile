package com.ipss.worldbank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.ipss.worldbank.database.SaveDataButtonListener;
import com.ipss.worldbank.database.SaveImageButtonListener;
import com.ipss.worldbank.entity.DataChart;
import com.ipss.worldbank.entity.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
        private static final String EXTRA_DATA = "extra_data";

        public static Intent newIntent(Context packageContext, DataChart dataChart) {
            Intent intent = new Intent(packageContext, ChartActivity.class);
            intent.putExtra(EXTRA_DATA, dataChart);
            return intent;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chart);

            LineChart chart = (LineChart) findViewById(R.id.chart);
            List<Entry> entries = new ArrayList<Entry>();

            DataChart dataChart = (DataChart) getIntent().getSerializableExtra(EXTRA_DATA);
            Iterator<Point> i = dataChart.getPointList().iterator();
            while (i.hasNext()) {
                Point p = i.next();
                // turn your data into Entry objects
                entries.add(new Entry(p.getYear(), (float) p.getValue()));
            }


            LineDataSet dataSet = new LineDataSet(entries, dataChart.getCountry() + " - " + dataChart.getIndicator()); // add entries to dataset
//        dataSet.setColor(...);
//        dataSet.setValueTextColor(...); // styling, ...

            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); // refresh

            Button saveImageBtn = findViewById(R.id.saveImageBtn);
            SaveImageButtonListener saveImageButtonListener = new SaveImageButtonListener(this,
                    chart, dataChart.getCountry(), dataChart.getIndicator());
            saveImageBtn.setOnClickListener(saveImageButtonListener);

            Button saveDataBtn = findViewById(R.id.saveDataBtn);
            SaveDataButtonListener saveDataButtonListener = new SaveDataButtonListener(this, dataChart);
            saveDataBtn.setOnClickListener(saveDataButtonListener);
        }
}
