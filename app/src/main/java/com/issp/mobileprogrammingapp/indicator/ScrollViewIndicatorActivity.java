package com.ipss.worldbank.indicator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ipss.worldbank.R;

import java.util.LinkedList;

public class ScrollViewIndicatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LinkedList<Indicator> indicators = new LinkedList<>();
        indicators.add(new Indicator("Indicatore della vita delle persone che si divertono a morire", "La sorgente dalla quale sgorga la vita, ma si è intoppata", "Leonardo die for life 4ever", "Università della strada"));
        indicators.add(new Indicator("Indicatore della vita delle persone che si divertono a morire", "La sorgente dalla quale sgorga la vita, ma si è intoppata", "Leonardo die for life 4ever", "Università della strada"));
        indicators.add(new Indicator("Indicatore della vita delle persone che si divertono a morire", "La sorgente dalla quale sgorga la vita, ma si è intoppata", "Leonardo die for life 4ever", "Università della strada"));
        indicators.add(new Indicator("Indicatore della vita delle persone che si divertono a morire", "La sorgente dalla quale sgorga la vita, ma si è intoppata", "Leonardo die for life 4ever", "Università della strada"));
        indicators.add(new Indicator("Indicatore della vita delle persone che si divertono a morire", "La sorgente dalla quale sgorga la vita, ma si è intoppata", "Leonardo die for life 4ever", "Università della strada"));
        indicators.add(new Indicator("Indicatore della vita delle persone che si divertono a morire", "La sorgente dalla quale sgorga la vita, ma si è intoppata", "Leonardo die for life 4ever", "Università della strada"));
        indicators.add(new Indicator("Indicatore della vita delle persone che si divertono a morire", "La sorgente dalla quale sgorga la vita, ma si è intoppata", "Leonardo die for life 4ever", "Università della strada"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new IndicatorAdapter(indicators, this));
    }
}
