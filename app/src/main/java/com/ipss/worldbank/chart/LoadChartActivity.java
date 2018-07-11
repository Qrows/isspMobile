package com.ipss.worldbank.chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ipss.worldbank.R;
import com.ipss.worldbank.entity.ChartImage;
import com.ipss.worldbank.entity.DataChart;
import com.ipss.worldbank.entity.Point;

import java.util.LinkedList;

public class LoadChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        LinkedList<ChartImage> chartImages = new LinkedList<>();
        chartImages.add(new ChartImage("Grafico 1", null));
        chartImages.add(new ChartImage("Grafico 2", null));
        chartImages.add(new ChartImage("Grafico 3", null));
        chartImages.add(new ChartImage("Grafico 4", null));

        LinkedList<DataChart> dataCharts = new LinkedList<>();

        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.load);
        ListView chartListView = findViewById(R.id.chartListView);
        LoadType type = fromOrdinalToLoadType(getIntent().getIntExtra("type", LoadType.CHART.ordinal()));
        if (type == LoadType.CHART) {
            chartListView.setAdapter(new ChartAdapter(chartImages, this));
        } else {
            chartListView.setAdapter(new DataAdapter(dataCharts, this));
        }
    }

    private LoadType fromOrdinalToLoadType(int ordinal) {
        switch (ordinal) {
            case 0:
                return LoadType.CHART;

            case 1:
                return LoadType.DATA;

            default:
                return null;
        }
    }
}
