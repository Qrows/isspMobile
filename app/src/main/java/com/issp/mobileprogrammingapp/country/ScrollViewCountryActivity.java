package com.ipss.worldbank.country;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ipss.worldbank.R;
import com.ipss.worldbank.country.Country;
import com.ipss.worldbank.country.CountryAdapter;

import java.util.LinkedList;

public class ScrollViewCountryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] names = {"Italy", "Angola", "Aruba", "Brazil", "China", "Japan"};
        String[] iso = {"IT", "AN", "AW", "BR", "CH", "JP"};
        super.onCreate(savedInstanceState);

        LinkedList<Country> countries = new LinkedList<>();
        for (int i = 0; i < names.length; i++) {
            countries.add(new Country(names[i], iso[i]));
        }
        setContentView(R.layout.activity_scrollview);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new CountryAdapter(countries, this));
    }
}
