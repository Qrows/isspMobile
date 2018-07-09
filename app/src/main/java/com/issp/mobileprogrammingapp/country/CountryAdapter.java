package com.ipss.worldbank.country;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ipss.worldbank.R;
import com.ipss.worldbank.country.Country;

import java.util.LinkedList;

public class CountryAdapter extends BaseAdapter {
    private LinkedList<Country> countries;
    private LayoutInflater inflater;

    public CountryAdapter(LinkedList<Country> countries, Context context) {
        this.countries = countries;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        if (position % 2 == 0) {
            rowView = inflater.inflate(R.layout.scrollview_country_item_light, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.scrollview_country_item_dark, parent, false);
        }

        TextView countryTv = rowView.findViewById(R.id.countryTv);
        TextView isoTv = rowView.findViewById(R.id.isocodeTv);
        Country country = countries.get(position);

        countryTv.setText(country.getName());
        countryTv.setTypeface(null, Typeface.BOLD);

        isoTv.setText(country.getIso());

        return rowView;
    }
}
