package com.ipss.worldbank.indicator;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ipss.worldbank.R;
import com.ipss.worldbank.custom_widget.CustomEditText;
import com.ipss.worldbank.entity.Indicator;
import com.ipss.worldbank.entity.Topic;
import com.ipss.worldbank.network.rest.WorldBanksRest;
import com.ipss.worldbank.topic.ScrollViewTopicActivity;
import com.ipss.worldbank.topic.TopicAdapter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class ScrollViewIndicatorActivity extends AppCompatActivity {
    private ListView listView;
    private ProgressBar progressBar;
    private CustomEditText searchEt;
    private ScrollViewIndicatorActivity context;
    private String starter;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_scrollview);

        searchEt = findViewById(R.id.searchEt);
        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.countryProgressBar);

        searchEt.setHint(R.string.indicator_hint);
        searchEt.setEnabled(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        context = this;
        progressBar.getIndeterminateDrawable().setColorFilter(getColor(R.color.spinner), PorterDuff.Mode.MULTIPLY);
        WorldBanksRest worldBanksRest = new WorldBanksRest(this);
        String topic = getIntent().getStringExtra("topic");
        Topic t = new Topic();
        t.setId(topic);
        worldBanksRest.getIndicatorsListFromTopic(t, new IndicatorListener(), new IndicatorErrorListener());
        starter = getIntent().getStringExtra("starter");
    }

    class IndicatorListener implements Response.Listener<Indicator[]> {
        @Override
        public void onResponse(final Indicator[] response) {
            progressBar.setVisibility(View.GONE);
            Arrays.sort(response, new Comparator<Indicator>() {
                @Override
                public int compare(Indicator o1, Indicator o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            listView.setAdapter(new IndicatorAdapter(response, context, starter));
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(response[position].getName());

                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                    return true;
                }
            });
            searchEt.setEnabled(true);
        }
    }

    class IndicatorErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            progressBar.setVisibility(View.GONE);
            AlertDialog.Builder alert = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
            alert.setTitle(R.string.error);
            alert.setMessage(R.string.error_msg);
            alert.setCancelable(false);
            alert.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

            alert.show();
        }
    }
}
