package com.ipss.worldbank.chart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ipss.worldbank.R;
import com.ipss.worldbank.entity.DataChart;

import java.util.LinkedList;

public class DataAdapter extends BaseAdapter {
    private LinkedList<DataChart> dataCharts;
    private Context context;
    private LayoutInflater inflater;

    public DataAdapter(LinkedList<DataChart> dataCharts, Context context) {
        this.dataCharts = dataCharts;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataCharts.size();
    }

    @Override
    public Object getItem(int position) {
        return dataCharts.get(position);
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
        chartName.setText(dataCharts.get(position).getName());

        return rowView;
    }
}
