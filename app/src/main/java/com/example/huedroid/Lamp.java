package com.example.huedroid;

import android.graphics.Color;

import com.example.huedroid.Connection.BridgeConnection;

public class Lamp {

    private int id;
    private boolean stateOn;
    private int stateBri;
    private int stateHue;
    private int stateSat;
    private int color;

    public Lamp(int id, boolean stateOn, int stateBri, int stateHue, int stateSat) {
        this.id = id;
        this.stateOn = stateOn;
        this.stateBri = stateBri;
        this.stateHue = stateHue;
        this.stateSat = stateSat;
        float[] hsv = new float[3];
        hsv[0] = (float)this.stateHue / (65535.0f/360.0f);
        hsv[1] = (float)this.stateSat / 254.0f;
        hsv[2] = ((float)this.stateBri - 1.0f) / 253.0f;
        this.color = Color.HSVToColor(hsv);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStateOn() {
        return stateOn;
    }

    public void setStateOn(boolean stateOn) {
        this.stateOn = stateOn;
    }

    public int getStateBri() {
        return stateBri;
    }

    public void setStateBri(int stateBri) {
        this.stateBri = stateBri;
    }

    public int getStateHue() {
        return stateHue;
    }

    public void setStateHue(int stateHue) {
        this.stateHue = stateHue;
    }

    public int getStateSat() {
        return stateSat;
    }

    public void setStateSat(int stateSat) {
        this.stateSat = stateSat;
    }

    public int getColor() {
        float[] hsv = new float[3];
        hsv[0] = (float)this.stateHue / (65535.0f/360.0f);
        hsv[1] = (float)this.stateSat / 254.0f;
        hsv[2] = ((float)this.stateBri - 1.0f) / 253.0f;
        this.color = Color.HSVToColor(hsv);
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
