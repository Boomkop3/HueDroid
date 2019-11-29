package com.example.huedroid;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.huedroid.Connection.BridgeConnection;

public class Lamp implements Parcelable {

    private int id;
    private boolean stateOn;
    private int stateBri;
    private int stateHue;
    private int stateSat;
    private int color;
    private BridgeConnection bridgeConnection;

    public Lamp(int id, boolean stateOn, int stateBri, int stateHue, int stateSat, BridgeConnection connection) {
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
        this.bridgeConnection = connection;
    }

    protected Lamp(Parcel in) {
        id = in.readInt();
        stateOn = in.readByte() != 0;
        stateBri = in.readInt();
        stateHue = in.readInt();
        stateSat = in.readInt();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (stateOn ? 1 : 0));
        dest.writeInt(stateBri);
        dest.writeInt(stateHue);
        dest.writeInt(stateSat);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Lamp> CREATOR = new Creator<Lamp>() {
        @Override
        public Lamp createFromParcel(Parcel in) {
            return new Lamp(in);
        }

        @Override
        public Lamp[] newArray(int size) {
            return new Lamp[size];
        }
    };

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
