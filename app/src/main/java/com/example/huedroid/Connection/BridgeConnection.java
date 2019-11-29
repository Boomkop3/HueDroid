package com.example.huedroid.Connection;

import android.content.Context;

public interface BridgeConnection {
    void setOn(Context context, boolean on, int id);
    void setHue(Context context, int hue, int id);
    void setBri(Context context, int bri, int id);
    void setSat(Context context, int sat, int id);
    boolean getOn(Context context, int id);
    int getHue(Context context, int id);
    int getBri(Context context, int id);
    int getSat(Context context, int id);
    int getId(Context context, int id);
}
