package com.example.krivoblotsky.itoilet;

import android.app.DownloadManager;
import android.content.Context;
import android.location.GpsStatus;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;

import org.json.JSONObject;

import java.lang.reflect.Method;

/**
 * Created by Krivoblotsky on 2/15/15.
 */
public class RequestManager {

    private Context context = null;
    private RequestQueue queue = null;
    private RequestManagerHandler handler = null;

    public RequestManager(Context _context, RequestManagerHandler _handler) {
        handler = _handler;
        context = _context;
        queue = Volley.newRequestQueue(_context);
    }

    /**
     * Public
     * */
    public void sendGETRequest(String url) {
        this.sendRequest(url, Request.Method.GET);
    }

    public void sendPOSTRequest(String url) {
        this.sendRequest(url, Request.Method.POST);
    }

    /**
     * Private
     * */
    private void sendRequest(final String url, int method) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.context);


        Response.Listener successListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                handler.requestCompleted(object, url);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                handler.requestFailed(volleyError, url);
            }
        };

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(method, url, null, successListener, errorListener);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
