package com.example.huedroid.Connection;

import android.content.Context;

public interface BridgeConnection {
    void setOn(Context context, boolean on, int id);
    void setHue(Context context, int hue, int id);
    void setBri(Context context, int bri, int id);
    void setSat(Context context, int sat, int id);
    void getOn(Context context, int id, LightsAPIManager.lightTextCallback callbackThingy);
    void getHue(Context context, int id, LightsAPIManager.lightTextCallback callbackThingy);
    void getBri(Context context, int id, LightsAPIManager.lightTextCallback callbackThingy);
    void getSat(Context context, int id, LightsAPIManager.lightTextCallback callbackThingy);
    void getIds(Context context, LightsAPIManager.idCallback callback);
    void getLights(Context context, LightsAPIManager.APIListener callback);
}
