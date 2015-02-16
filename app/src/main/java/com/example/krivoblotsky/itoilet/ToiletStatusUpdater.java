package com.example.krivoblotsky.itoilet;

import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Krivoblotsky on 2/15/15.
 */
public class ToiletStatusUpdater implements RequestManagerHandler {

    /* Private vars */
    private Context context = null;
    private RequestManager currentRequestManager = null;
    private ToiletStatusUpdaterHandler handler = null;

    /* Content URL */
    static public String contentUpdateURL = "http://toilet.demo.alterplay.com";
    static private String lightStatusKey = "light_status";

    public ToiletStatusUpdater(Context _context, ToiletStatusUpdaterHandler _handler) {
        context = _context;
        handler = _handler;
    }

    /**
     * Public
     * */

    public void updateStatus() {
        RequestManager manager = this.requestManager();
        manager.sendGETRequest(contentUpdateURL);
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

         try {

             boolean busy = object.getBoolean(lightStatusKey);
             if (busy) {
                 handler.toiletStatusUpdated(ToiletStatus.ToiletStatusBusy);
             } else {
                 handler.toiletStatusUpdated(ToiletStatus.ToiletStatusFree);
             }

         } catch (Exception e) {
            handler.toiletStatusUpdated(ToiletStatus.ToiletStausUnavaliable);
         }
     }

     public void requestFailed(VolleyError error, String url) {
        handler.toiletStatusUpdated(ToiletStatus.ToiletStausUnavaliable);
     }
}
