package com.ipss.worldbank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ipss.worldbank.chart.LoadChartActivity;
import com.ipss.worldbank.chart.LoadType;
import com.ipss.worldbank.country.ScrollViewCountryActivity;
import com.ipss.worldbank.indicator.ScrollViewIndicatorActivity;
import com.ipss.worldbank.topic.ScrollViewTopicActivity;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        ImageView base1 = findViewById(R.id.base1);
        ImageView base2 = findViewById(R.id.base2);
        ImageView base3 = findViewById(R.id.base3);
        ImageView base4 = findViewById(R.id.base4);

        base1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ScrollViewCountryActivity.class));
            }
        });

        base2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ScrollViewTopicActivity.class));
            }
        });

        base3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LoadChartActivity.class).putExtra("type", LoadType.CHART.ordinal()));
            }
        });

        base4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LoadChartActivity.class).putExtra("type", LoadType.DATA.ordinal()));
            }
        });
    }
}