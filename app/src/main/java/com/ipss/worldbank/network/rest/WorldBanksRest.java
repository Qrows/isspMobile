package com.ipss.worldbank.network.rest;

import android.content.Context;

import com.android.volley.Response;

import com.ipss.worldbank.entity.Country;
import com.ipss.worldbank.entity.Indicator;
import com.ipss.worldbank.entity.IndicatorData;
import com.ipss.worldbank.entity.PageMetaData;
import com.ipss.worldbank.entity.Topic;
import com.ipss.worldbank.network.NetworkController;


public class WorldBanksRest {
    /** The WorldBanKRest Class handle the REST call to the worldbank web site.
     *  It support:
     *    + Fetch all country data
     *    + Fetch all topics
     *    + Fetch all Indicator given a topic
     *    + Fetch all data given a country and a indicator
     * */
    private NetworkController networkController;
    private static final String slash = "/";
    private static final String qm = "?";
    private static final String and = "&";
    private static final String wbBaseUrl = "https://api.worldbank.org/v2/";
    private static final String wbCountryUrl = "country";
    private static final String wbCountriesUrl = "countries";
    private static final String wbIndicatorUrl = "indicators";
    private static final String wbTopicsUrl = "topics";
    private static final String wbJsonFormat = "format=json";
    private static final String wbperPage = "per_page=";

    public WorldBanksRest(Context ctx) {
        networkController = NetworkController.getInstance(ctx);
    }

    public void getCountryList(Response.Listener<Country[]> listener,
                               Response.ErrorListener errorListener) {
        /**
         * The getCountryList() fetch all country data from worldbank in a asynchronous way.
         * @param listener: handle what to do when the request is successful.
         * @param ErrorListener: handle what happen when the request is unsuccessful.
         */
        String baseUrl = wbBaseUrl + wbCountryUrl + qm + wbJsonFormat + and + wbperPage;
        // request to fetch metadata and then fetch all country, CountryListener fetch all country.
        CountryListener countryListener = new CountryListener(baseUrl, listener, errorListener);
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1,  countryListener, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }

    public void getTopicsList(Response.Listener<Topic[]> listener,
                              Response.ErrorListener errorListener) {
        /**
         * The getTopicsList() fetch all topic data from worldbank in a asynchronous way.
         * @param listener: handle what to do when the request is successful.
         * @param ErrorListener: handle what happen when the request is unsuccessful.
         */
        String baseUrl = wbBaseUrl + wbTopicsUrl + qm + wbJsonFormat + and + wbperPage;
        // request to fetch metadata and then fetch all topic, TopicsListener fetch all country.
        TopicsListener topicsListener = new TopicsListener(baseUrl, listener, errorListener);
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1, topicsListener, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }

    public void getIndicatorsListFromTopic(
            Topic topic,
            Response.Listener<Indicator[]> listener,
            Response.ErrorListener errorListener) {
        /**
         * The getIndicatorsListFromTopic() fetch all indicator data regarding a topic
         * from worldbank in a asynchronous way.
         * @param topic: the topic of all indicator.
         * @param listener: handle what to do when the request is successful.
         * @param ErrorListener: handle what happen when the request is unsuccessful.
         */

        String baseUrl = wbBaseUrl + wbTopicsUrl + slash + topic.getId() + slash + wbIndicatorUrl +
                qm + wbJsonFormat + and + wbperPage;
        // request to fetch metadata and then fetch all requested indicator,
        // IndicatorListener fetch all indicator.
        IndicatorListener indicatorListener = new IndicatorListener(baseUrl, listener, errorListener);
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1, indicatorListener, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }

    public void getDataFromCountryAndIndicator(Country country,
                                        Indicator indicator,
                                        Response.Listener<IndicatorData[]> listener,
                                        Response.ErrorListener errorListener) {
        /**
         * The getDataFromCountryAndIndicator() fetch all data regarding
         * a contry and a indicator from worldbank in a asynchronous way.
         * @param country: country of which we want to get data, need iso2code.
         * @param indicator: indicator, the kind of data we want to receive. need id.
         * @param listener: handle what to do when the request is successful.
         * @param ErrorListener: handle what happen when the request is unsuccessful.
         */

        String baseUrl = wbBaseUrl + wbCountriesUrl + slash + country.getIso2code() + slash
                + wbIndicatorUrl + slash + indicator.getId() + qm + wbJsonFormat + and + wbperPage;
        // request to fetch metadata and then fetch all Data, IndicatorDataListener fetch all country.
        IndicatorDataListener indicatorDataListener = new IndicatorDataListener(baseUrl, listener, errorListener);
        PageMetaDataRequest mdRequest = new PageMetaDataRequest(baseUrl + 1, indicatorDataListener, errorListener);
        networkController.addToRequestQueue(mdRequest);
    }

    abstract class PageMetaDataListener implements Response.Listener<PageMetaData> {
        /**
         * PageMetaDataListener class fetch the metadata from a worldbanks request.
         * It permit to receive how many entry has the data that we want to request,
         * so that we can fetch all data in a single request.
         * For the single specific request extends this class to implements
         * the specific domain logic.
         * */
        private String baseUrl;
        private Response.Listener listener;
        private Response.ErrorListener errorListener;
        public PageMetaDataListener(String baseUrl, Response.Listener listener, Response.ErrorListener errorListener) {
            this.baseUrl = baseUrl;
            this.listener = listener;
            this.errorListener = errorListener;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public Response.Listener getListener() {
            return listener;
        }

        public void setListener(Response.Listener listener) {
            this.listener = listener;
        }

        public Response.ErrorListener getErrorListener() {
            return errorListener;
        }

        @Override
        public abstract void onResponse(PageMetaData response);
    }

    class CountryListener extends PageMetaDataListener {
        /**
         * CountryListener class fetch all metadata, like the number of country,
         * and send a second request asking for all the country in a single request
         * */
        public CountryListener(String baseUrl, Response.Listener<Country[]> listener, Response.ErrorListener errorListener) {
            super(baseUrl, listener, errorListener);
        }

        @Override
        public void onResponse(PageMetaData response) {
            String url = this.getBaseUrl() + response.getTotal();
            CountryRequest request = new CountryRequest(url, this.getListener(), this.getErrorListener());
            networkController.addToRequestQueue(request);
        }
    }

    class TopicsListener extends PageMetaDataListener {
        /**
         * TopicsListener class fetch all metadata, like the number of topics,
         * and send a second request asking for all the topics in a single request
         * */

        public TopicsListener(String baseUrl, Response.Listener<Topic[]> listener, Response.ErrorListener errorListener) {
            super(baseUrl, listener, errorListener);
        }

        @Override
        public void onResponse(PageMetaData response) {
            String url = this.getBaseUrl() + response.getTotal();
            TopicsRequest request = new TopicsRequest(url,
                    this.getListener(), this.getErrorListener());
            networkController.addToRequestQueue(request);
        }
    }

    class IndicatorListener extends PageMetaDataListener {
        /**
         * TopicsListener class fetch all metadata, like the number of indicator,
         * and send a second request asking for all the requested indicator in a single request
         * */

        public IndicatorListener(String baseUrl, Response.Listener<Indicator[]> listener, Response.ErrorListener errorListener) {
            super(baseUrl, listener, errorListener);
        }

        @Override
        public void onResponse(PageMetaData response) {
            String url = this.getBaseUrl() + response.getTotal();
            IndicatorRequest request = new IndicatorRequest(url,
                    this.getListener(), this.getErrorListener());
            networkController.addToRequestQueue(request);
        }
    }

    class IndicatorDataListener extends PageMetaDataListener {
        /**
         * TopicsListener class fetch all metadata, like the number of Data,
         * and send a second request asking for all the requested Data in a single request
         * */

        public IndicatorDataListener(String baseUrl, Response.Listener<IndicatorData[]> listener, Response.ErrorListener errorListener) {
            super(baseUrl, listener, errorListener);
        }

        @Override
        public void onResponse(PageMetaData response) {
            String url = this.getBaseUrl() + response.getTotal();
            IndicatorDataRequest request = new IndicatorDataRequest(url,
                    this.getListener(), this.getErrorListener());
            networkController.addToRequestQueue(request);

        }
    }
}
