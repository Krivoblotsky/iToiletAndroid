package com.example.krivoblotsky.itoilet;

import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Krivoblotsky on 2/15/15.
 */
public class ToiletStatusUpdater implements RequestManagerHandler {

    private Context context = null;
    private RequestManager currentRequestManager = null;
    private ToiletStatusUpdaterHandler handler = null;

    public ToiletStatusUpdater(Context _context, ToiletStatusUpdaterHandler _handler) {
        context = _context;
        handler = _handler;
    }

    /**
     * Public
     * */

    public void updateStatus() {
        RequestManager manager = this.requestManager();
        manager.sendGETRequest("http://zoomle.demo.alterplay.com/api/");
    }

    /**
     * Request manager lazy
     * */

     public RequestManager requestManager() {
         if (currentRequestManager == null) {
             currentRequestManager = new RequestManager(this.context, this);
         }
         return currentRequestManager;
     }

    /**
     * RequestManagerHandler
     * */

     public void requestCompleted(JSONObject object, String url) {
        handler.toiletStatusUpdated(ToiletStatus.ToiletStatusFree);
     }

     public void requestFailed(VolleyError error, String url) {
        handler.toiletStatusUpdated(ToiletStatus.ToiletStausUnavaliable);
     }
}