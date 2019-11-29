package com.example.huedroid.Connection;

import android.content.Context;

import java.util.ArrayList;

public interface BridgeConnection {
    void setOn(Context context, boolean on, int id);
    void setHue(Context context, int hue, int id);
    void setBri(Context context, int bri, int id);
    void setSat(Context context, int sat, int id);
    void getOn(Context context, int id, LightsAPIManager.myCallbackThingy callbackThingy);
    void getHue(Context context, int id, LightsAPIManager.myCallbackThingy callbackThingy);
    void getBri(Context context, int id, LightsAPIManager.myCallbackThingy callbackThingy);
    void getSat(Context context, int id, LightsAPIManager.myCallbackThingy callbackThingy);
    void getIds(Context context, LightsAPIManager.idCallback callback);
}
