package com.ipss.worldbank.network.rest;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.ipss.worldbank.entity.Indicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class IndicatorRequest extends Request<Indicator[]> {
    /**
     * IndicatorRequest is a specialized kind of request which get data from a given url
     * using an HTTP GET method and parse it to Indicator[] objects.
     * This class doesn't do networking work.
     * The response is expected to be of json format, if not the request will fail.
     * */

    private Response.Listener<Indicator[]> listener;

    public IndicatorRequest(String url, Response.Listener<Indicator[]> listener,
                            Response.ErrorListener errorListener) {
        /**
         * @param url where send the HTTP GET request
         * @param listener tell what to do when the request receive ha successful response
         * @param errorListener tell what to do when the request fail
         * */

        super(Method.GET, url, errorListener);
        this.listener = listener;
    }

    @Override
    protected Response<Indicator[]> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data);
            String header = new String(HttpHeaderParser.parseCharset(response.headers));
            JSONArray array = new JSONArray(json);
            JSONArray indicatorsArray = array.getJSONArray(1);
            Indicator[] indicators = jsonArrayToIndicators(indicatorsArray);
            return Response.success(indicators,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }


    @Override
    protected void deliverResponse(Indicator[] response) {
        listener.onResponse(response);
    }

    private Indicator jsonToIndicator(JSONObject jobj) throws JSONException{
        try {
            Indicator indicator = new Indicator();
            indicator.setId(jobj.getString("id"));
            indicator.setName(jobj.getString("name"));
            JSONObject source = jobj.getJSONObject("source");
            indicator.setSource(source.getString("value"));
            indicator.setSourceDescription(jobj.getString("sourceNote"));
            indicator.setOrganizationName(jobj.getString("sourceOrganization"));
            return indicator;
        } catch (JSONException e) {
            throw e;
        }
    }

    private Indicator[] jsonArrayToIndicators(JSONArray jArray) {
        LinkedList<Indicator> iList = new LinkedList<>();
        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject curr = jArray.getJSONObject(i);
                Indicator in = jsonToIndicator(curr);
                iList.add(in);
            } catch (JSONException e) {

            }
        }
        return iList.toArray(new Indicator[iList.size()]);
    }
}
