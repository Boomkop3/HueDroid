package com.example.huedroid.Connection;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
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
    public void getUsername(String ip, int port, String devicetype, OnEmulatorUsername callback){
        String url = "http://" + ip + ":" + port + "/api";
        HashMap map = new HashMap();
        map.put("devicetype", devicetype);
        JSONObject body = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body, null, null);
    }
    public interface OnEmulatorUsername {
        public void respond(String username);
    }
}
