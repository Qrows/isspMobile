package com.issp.mobileprogrammingapp.network.REST;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.issp.mobileprogrammingapp.entity.Country;
import com.issp.mobileprogrammingapp.entity.Indicator;
import com.issp.mobileprogrammingapp.entity.PageMetaData;
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

    public void getCountryList(final Response.Listener<Country[]> listener,
                               final Response.ErrorListener errorListener) {
        final String baseUrl = wbBaseUrl + wbCountryUrl + qm + wbJsonFormat + and + wbperPage;
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1,
                new Response.Listener<PageMetaData>() {
                    @Override
                    public void onResponse(PageMetaData response) {
                        String url = baseUrl + response.getTotal();
                        CountryRequest request = new CountryRequest(url, listener, errorListener);
                        networkController.addToRequestQueue(request);
                    }
                }, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }

    public void getTopicsList(final Response.Listener<Topic[]> listener,
                              final Response.ErrorListener errorListener) {
        final String baseUrl = wbBaseUrl + wbTopicsUrl + qm + wbJsonFormat + and + wbperPage;
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1,
                new Response.Listener<PageMetaData>() {
                    @Override
                    public void onResponse(PageMetaData response) {
                        String url = baseUrl + response.getTotal();
                        TopicsRequest request = new TopicsRequest(url,
                                listener, errorListener);
                        networkController.addToRequestQueue(request);
                    }
                }, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }

    public void getIndicatorsListFromTopic(
            Topic topic,
            final Response.Listener<Indicator[]> listener,
            final Response.ErrorListener errorListener) {
        final String baseUrl = wbBaseUrl + wbTopicsUrl + slash + topic.getId() + slash + wbIndicatorUrl +
                qm + wbJsonFormat + wbperPage;
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1,
                new Response.Listener<PageMetaData>() {
                    @Override
                    public void onResponse(PageMetaData response) {
                        String url = baseUrl + response.getTotal();
                        IndicatorRequest request = new IndicatorRequest(url,
                                listener, errorListener);
                        networkController.addToRequestQueue(request);
                    }
                }, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }

    public void getIndicatorFromCountry(Country country,
                                        Indicator indicator,
                                        final Response.Listener<Indicator[]> listener,
                                        final Response.ErrorListener errorListener) {
        final String baseUrl = wbBaseUrl + wbCountriesUrl + slash + country.getIso2code() + slash
                + wbIndicatorUrl + slash + indicator.getId() + qm + wbJsonFormat + and + wbperPage;
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1,
                new Response.Listener<PageMetaData>() {
                    @Override
                    public void onResponse(PageMetaData response) {
                        String url = baseUrl + response.getTotal();
                        IndicatorRequest request = new IndicatorRequest(url,
                                listener, errorListener);
                        networkController.addToRequestQueue(request);
                    }
                }, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }
}
