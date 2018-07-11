package com.ipss.worldbank.network.rest;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.ipss.worldbank.entity.PageMetaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PageMetaDataRequest extends Request<PageMetaData> {
    /**
     * PageMetaDataRequestis a specialized kind of request which get data from a given url
     * using an HTTP GET method and parse it to PageMetaData object.
     * This class doesn't do networking work.
     * The response is expected to be of json format, if not the request will fail.
     * */

    private Response.Listener<PageMetaData> listener;
    public PageMetaDataRequest(String url,
                               Response.Listener<PageMetaData> listener,
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
    protected Response<PageMetaData> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data);
            String header = new String(HttpHeaderParser.parseCharset(response.headers));
            JSONArray array = new JSONArray(json);
            JSONObject jsonObject = array.getJSONObject(0);
            PageMetaData metaData = new PageMetaData();
            metaData.setPage(jsonObject.getInt("page"));
            metaData.setPages(jsonObject.getInt("pages"));
            metaData.setPer_page(jsonObject.getInt("per_page"));
            metaData.setTotal(jsonObject.getInt("total"));
            return Response.success(metaData, HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(PageMetaData response) {
        listener.onResponse(response);
    }
}
