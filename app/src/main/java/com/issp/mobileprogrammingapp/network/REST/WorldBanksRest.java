package com.issp.mobileprogrammingapp.network.REST;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import com.issp.mobileprogrammingapp.entity.Topic;
import com.issp.mobileprogrammingapp.network.NetworkController;

import org.json.JSONArray;


public class WorldBanksRest {
    private NetworkController networkController;
    private static final String slash = "\\";
    private static final String qm = "?";
    private static final String and = "&";
    private static final String wbBaseUrl = "https://api.worldbank.org/v2/";
    private static final String wbCountryUrl = "country";
    private static final String wbCountriesUrl = "countries";
    private static final String wbIndicatorUrl = "indicator";
    private static final String wbTopicsUrl = "topics";
    private static final String wbJsonFormat = "format=json";
    private static final String wbperPage = "per_page=";

    public WorldBanksRest(Context ctx) {
        networkController = NetworkController.getInstance(ctx);
    }

}
