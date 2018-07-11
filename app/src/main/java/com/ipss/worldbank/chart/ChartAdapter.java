package com.ipss.worldbank.chart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ipss.worldbank.R;
import com.ipss.worldbank.entity.ChartImage;

import java.util.LinkedList;

public class ChartAdapter extends BaseAdapter {
    private LinkedList<ChartImage> chartImages;
    private Context context;
    private LayoutInflater inflater;

    public ChartAdapter(LinkedList<ChartImage> chartImages, Context context) {
        this.chartImages = chartImages;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return chartImages.size();
    }

    @Override
    public Object getItem(int position) {
        return chartImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        if (position % 2 == 0) {
            rowView = inflater.inflate(R.layout.load_chart_item_light, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.load_chart_item_dark, parent, false);
        }

        TextView chartName = rowView.findViewById(R.id.chartNameTv);
        chartName.setText(chartImages.get(position).getName());

        return rowView;
    }
}
