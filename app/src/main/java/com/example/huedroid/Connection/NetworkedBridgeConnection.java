package com.example.huedroid.Connection;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.huedroid.OnResponse;

public class NetworkedBridgeConnection implements BridgeConnection, Parcelable {
    private String ipAdress;
    private int port;
    private String userName;
    public NetworkedBridgeConnection(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
    }

    protected NetworkedBridgeConnection(Parcel in) {
        ipAdress = in.readString();
        port = in.readInt();
        userName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ipAdress);
        dest.writeInt(port);
        dest.writeString(userName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NetworkedBridgeConnection> CREATOR = new Creator<NetworkedBridgeConnection>() {
        @Override
        public NetworkedBridgeConnection createFromParcel(Parcel in) {
            return new NetworkedBridgeConnection(in);
        }

        @Override
        public NetworkedBridgeConnection[] newArray(int size) {
            return new NetworkedBridgeConnection[size];
        }
    };

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void getConnectionUsername(Context context, final OnResponse callback) {
        LightsAPIManager.getInstance(context).getUsername(
                ipAdress,
                port,
                "user",
                new LightsAPIManager.OnEmulatorUsername() {
                    @Override
                    public void respond(String username) {
                        setUserName(username);
                        callback.response();
                    }
                });
    }

    @Override
    public void setOn(Context context, boolean on, int id) {
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, userName, id, "on", on);
    }

    @Override
    public void setHue(Context context, int hue, int id) {
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, userName, id, "hue", hue);
    }

    @Override
    public void setBri(Context context, int bri, int id) {
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, userName, id, "bri", bri);
    }

    @Override
    public void setSat(Context context, int sat, int id) {
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, userName, id, "sat", sat);
    }

    @Override
    public void getOn(Context context, int id, LightsAPIManager.lightTextCallback lightTextCallback) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "on", lightTextCallback);
    }

    @Override
    public void getHue(Context context, int id, LightsAPIManager.lightTextCallback lightTextCallback) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "hue", lightTextCallback);
    }

    @Override
    public void getBri(Context context, int id, LightsAPIManager.lightTextCallback lightTextCallback) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "bri", lightTextCallback);
    }

    @Override
    public void getSat(Context context, int id, LightsAPIManager.lightTextCallback lightTextCallback) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "sat", lightTextCallback);
    }

    @Override
    public void getIds(Context context, LightsAPIManager.idCallback callback) {
        LightsAPIManager.getInstance(context).getId(ipAdress, port, userName, callback);
    }

    @Override
    public void getLights(Context context, LightsAPIManager.APIListener callback) {
        LightsAPIManager.getInstance(context).getLamps(this.getIpAdress(), this.getPort(), this.getUserName(), callback, this);
    }
}