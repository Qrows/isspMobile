package com.issp.mobileprogrammingapp.network.REST;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.issp.mobileprogrammingapp.entity.PageMetaData;
import com.issp.mobileprogrammingapp.network.NetworkController;

public class PageMetaDataListener<PageMetaData> extends Response<PageMetaData>.Listener {

    private Request request;
    private NetworkController networkController;

    public PageMetaDataListener(Context context,
                                Request request) {
        this.request = request;
        networkController = NetworkController.getInstance(context);
    }

    @Override
    public void onResponse(PageMetaData response) {
        networkController.addToRequestQueue(request);
    }
}
