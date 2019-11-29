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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    public void sendToState(String ip, int port, String username, int id, String bodyName, Object bodyContents) {
        String URL = "http://" + ip + ":" + port + "/api/" + username + "/lights/" + id + "/state";
        HashMap map = new HashMap();
        map.put(bodyName, bodyContents);
        JSONObject object = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        this.queue.add(request);
        this.queue.start();
    }

    public void getFromState(String ip, int port, String username, final int id, final String bodyName, final myCallbackThingy callback) {
        String URL = "http://" + ip + ":" + port + "/api/" + username + "/lights/" + id + "/state";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String requestString = response.getJSONObject("lights").getJSONObject(String.valueOf(id)).getJSONObject("state").get(bodyName).toString();
                    callback.respond(requestString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        this.queue.add(request);
        this.queue.start();
    }
    public interface myCallbackThingy {
        public void respond(String mytext);
    }

    public void getId(String ip, int port, String username, final idCallback callback) {
        String URL = "http://" + ip + ":" + port + "/api/" + username + "/lights";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Integer> ids = new ArrayList<>();
                    Iterator<String> iterator = response.keys();
                    while (iterator.hasNext()) {
                        ids.add(Integer.valueOf(iterator.next()));
                    }
                    callback.respond(ids);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        this.queue.add(request);
        this.queue.start();
    }
    public interface idCallback {
        public void respond(ArrayList<Integer> id);
    }
}
