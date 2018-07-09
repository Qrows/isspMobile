package com.issp.mobileprogrammingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.issp.mobileprogrammingapp.entity.Country;
import com.issp.mobileprogrammingapp.entity.Indicator;
import com.issp.mobileprogrammingapp.entity.Topic;
import com.issp.mobileprogrammingapp.network.NetworkController;
import com.issp.mobileprogrammingapp.network.REST.CountryRequest;
import com.issp.mobileprogrammingapp.network.REST.IndicatorRequest;
import com.issp.mobileprogrammingapp.network.REST.TopicsRequest;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    private NetworkController net;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.ResponseText);
        net = NetworkController.getInstance(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://api.worldbank.org/v2/indicators?format=json";
                textView.setText(url);
                final Request req = new IndicatorRequest(url,
                        new Response.Listener<Indicator[]>() {
                            @Override
                            public void onResponse(Indicator[] response) {
                                textView.setText(response[0].getId());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                textView.setText("ERROR");
                            }
                        });
                net.addToRequestQueue(req);
            }
        });
    }
}
