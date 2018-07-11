package com.ipss.worldbank.topic;

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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ipss.worldbank.R;
import com.ipss.worldbank.custom_widget.CustomEditText;
import com.ipss.worldbank.entity.Topic;
import com.ipss.worldbank.network.rest.WorldBanksRest;

import java.util.Arrays;
import java.util.Comparator;

public class ScrollViewTopicActivity extends AppCompatActivity {
    private ListView listView;
    private ProgressBar progressBar;
    private CustomEditText searchEt;
    private ScrollViewTopicActivity context;
    private String starter;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_scrollview);

        searchEt = findViewById(R.id.searchEt);
        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.countryProgressBar);

        searchEt.setHint(R.string.topic_hint);
        searchEt.setEnabled(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        context = this;
        progressBar.getIndeterminateDrawable().setColorFilter(getColor(R.color.spinner), PorterDuff.Mode.MULTIPLY);
        WorldBanksRest worldBanksRest = new WorldBanksRest(this);
        starter = getIntent().getStringExtra("starter");
        worldBanksRest.getTopicsList(new ScrollViewTopicActivity.TopicListener(), new ScrollViewTopicActivity.TopicErrorListener());
    }

    class TopicListener implements Response.Listener<Topic[]> {
        @Override
        public void onResponse(final Topic[] response) {
            progressBar.setVisibility(View.GONE);
            Arrays.sort(response, new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            Topic[] topicsToFilter = new Topic[response.length];
            for (int i = 0; i < topicsToFilter.length; i++) {
                topicsToFilter[i] = response[i];
            }

            TopicAdapter adapter = new TopicAdapter(topicsToFilter, context, starter);
            searchEt.addTextChangedListener(new TopicTextWatcher(response, topicsToFilter, adapter));
            listView.setAdapter(adapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(response[position].getValue());

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

    class TopicErrorListener implements Response.ErrorListener {

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