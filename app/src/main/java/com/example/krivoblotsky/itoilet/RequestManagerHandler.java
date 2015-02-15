package com.example.krivoblotsky.itoilet;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Krivoblotsky on 2/15/15.
 */
public interface RequestManagerHandler {

    public void requestCompleted(JSONObject object, String url);
    public void requestFailed(VolleyError error, String url);

}
