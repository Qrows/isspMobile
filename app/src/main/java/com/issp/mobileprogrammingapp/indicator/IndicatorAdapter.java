package com.ipss.worldbank.indicator;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ipss.worldbank.R;

import java.util.LinkedList;

public class IndicatorAdapter extends BaseAdapter {
    private LinkedList<Indicator> indicators;
    private Context context;
    private LayoutInflater inflater;

    public IndicatorAdapter(LinkedList<Indicator> indicators, Context context) {
        this.indicators = indicators;
        this.context = context;
        inflater = inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return indicators.size();
    }

    @Override
    public Object getItem(int position) {
        return indicators.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        if (position % 2 == 0) {
            rowView = inflater.inflate(R.layout.scrollview_indicator_item_light, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.scrollview_indicator_item_dark, parent, false);
        }

        TextView indicatorNameTv = rowView.findViewById(R.id.indicatorNameTv);
        TextView sourceNameTv = rowView.findViewById(R.id.sourceNameTv);
        Button helpBtn = rowView.findViewById(R.id.indicatorHelpBtn);
        final Indicator indicator = indicators.get(position);

        indicatorNameTv.setText(indicator.getName());
        sourceNameTv.setText(indicator.getSource());
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("Source description:\n" + indicator.getSourceDescription() + "\n\nOrganization:\n" + indicator.getOrganizationName());
                dialog.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return rowView;
    }
}
