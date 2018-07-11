package com.ipss.worldbank.country;

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
import com.ipss.worldbank.entity.Country;
import com.ipss.worldbank.network.rest.WorldBanksRest;

import java.util.Arrays;
import java.util.Comparator;


public class ScrollViewCountryActivity extends AppCompatActivity {
    private ListView listView;
    private ScrollViewCountryActivity context;
    private ProgressBar progressBar;
    private CustomEditText editText;
    private String starter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.countryProgressBar);
        editText = findViewById(R.id.searchEt);

        editText.setEnabled(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        context = this;
        progressBar.getIndeterminateDrawable().setColorFilter(getColor(R.color.spinner), PorterDuff.Mode.MULTIPLY);
        WorldBanksRest worldBanksRest = new WorldBanksRest(this);
        worldBanksRest.getCountryList(new CountryListener(), new CountryErrorListener());
        starter = getIntent().getStringExtra("starter");
    }

    class CountryListener implements Response.Listener<Country[]> {
        @Override
        public void onResponse(final Country[] response) {
            progressBar.setVisibility(View.GONE);
            Arrays.sort(response, new Comparator<Country>() {
                @Override
                public int compare(Country o1, Country o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            listView.setAdapter(new CountryAdapter(response, context, starter));
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(response[position].getName());

                    Toast toast = new Toast(getApplicationContext());
                    //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                    return true;
                }
            });
            editText.setEnabled(true);
        }
    }

    class CountryErrorListener implements Response.ErrorListener {

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
