package com.example.huedroid.Connection;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LightsAPIManager {
    private Context context;
    private RequestQueue queue;
    private static LightsAPIManager instance;
    private LightsAPIManager (Context context){
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
    }
    public static LightsAPIManager getInstance(Context context){
        if (instance == null) instance = new LightsAPIManager(context);
        return instance;
    }
    public void getUsername(String ip, int port, String devicetype, final OnEmulatorUsername callback){
        String url = "http://" + ip + ":" + port + "/api";
        HashMap map = new HashMap();
        map.put("devicetype", devicetype);
        final String body = new JSONObject(map).toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray data = response;
                try {
                    String username = data.getJSONObject(0).getJSONObject("success").getString("username");
                    callback.respond(username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int x = 666;
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    int x = 666;
                    // ToDo do something with this error
                }
            }
        ){
            @Override
            public byte[] getBody() {
                return body.getBytes();
            }
        };

        this.queue.add(request);
        this.queue.start();
    }
    public interface OnEmulatorUsername {
        public void respond(String username);
    }
}
