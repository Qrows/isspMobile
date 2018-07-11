package com.ipss.worldbank.network.rest;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.ipss.worldbank.entity.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class TopicsRequest extends Request<Topic[]> {
    /**
     * TopicsRequest is a specialized kind of request which get data from a given url
     * using an HTTP GET method and parse it to Topic[] objects.
     * This class doesn't do networking work.
     * The response is expected to be of json format, if not the request will fail.
     * */

    Response.Listener<Topic[]> listener;

    public TopicsRequest(String url, Response.Listener<Topic[]> listener ,
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
    protected Response<Topic[]> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data);
            String header = new String(HttpHeaderParser.parseCharset(response.headers));
            JSONArray array = new JSONArray(json);
            JSONArray topicsArray = array.getJSONArray(1);
            Topic[] topics = jsonArrayToTopics(topicsArray);
        return Response.success(topics,
                HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Topic[] response) {
        listener.onResponse(response);
    }

    private Topic jsonToTopic(JSONObject jobj) throws JSONException {
        try {
            Topic topic = new Topic();
            topic.setId(jobj.getString("id"));
            topic.setValue(jobj.getString("value"));
            topic.setSourceNote(jobj.getString("sourceNote"));
            return topic;
        } catch (JSONException e) {
            throw e;
        }
    }

    private Topic[] jsonArrayToTopics(JSONArray jArray) {
        LinkedList<Topic> tList = new LinkedList<>();
        for (int i = 0; i < jArray.length(); i++) {
            try {
                JSONObject curr = jArray.getJSONObject(i);
                Topic t = jsonToTopic(curr);
                tList.add(t);
            } catch (JSONException e) {

            }
        }
        return tList.toArray(new Topic[tList.size()]);
    }
}
