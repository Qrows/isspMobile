package com.ipss.worldbank.database;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.ipss.worldbank.entity.DataChart;

import java.util.ConcurrentModificationException;

public class SaveDataButtonListener implements View.OnClickListener {
    private static final String TAG = "SaveDataButtonListener";
    private Context context;
    private DataChart dataChart;

    public SaveDataButtonListener(Context context, DataChart dataChart) {
        this.context = context;
        this.dataChart = dataChart;
    }

    @Override
    public void onClick(View v) {
        Dao dao = Dao.get(context);
        try {
            dao.addDataChart(dataChart);
            Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Cannot save data", Toast.LENGTH_SHORT).show();
        }
    }
}
