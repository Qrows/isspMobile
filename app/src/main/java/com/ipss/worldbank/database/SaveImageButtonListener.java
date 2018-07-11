package com.ipss.worldbank.database;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SaveImageButtonListener implements View.OnClickListener {
    private static final String TAG = "SaveImageButtonListener";
    private Context context;
    private LineChart chart;
    private String country;
    private String indicator;

    public SaveImageButtonListener(Context context, LineChart chart,
                                   String country, String indicator) {
        this.context = context;
        this.chart = chart;
        this.country = country;
        this.indicator = indicator;
    }

    public static Bitmap loadBitmapFromView(Context context, View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    private String saveToInternalStorage(Bitmap bitmapImage) throws Exception {
        ContextWrapper cw = new ContextWrapper(context); //(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        String filename = country + "-" + indicator + "-" +
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date())
                + ".png";
        File mypath = new File(directory, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    @Override
    public void onClick(View v) {
//        chart.saveToGallery("chart", 100);

        Bitmap b = loadBitmapFromView(context, chart);
        try {
            saveToInternalStorage(b);
            Toast.makeText(context, /*FIXME*/"CHANGEMEPLS", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "Cannot save image", Toast.LENGTH_LONG).show();
        }
    }
}
