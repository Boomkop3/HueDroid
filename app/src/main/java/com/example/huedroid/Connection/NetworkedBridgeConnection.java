package com.example.huedroid.Connection;

import android.content.Context;

import com.example.huedroid.OnResponse;

import java.util.ArrayList;

public class NetworkedBridgeConnection implements BridgeConnection {
    private String ipAdress;
    private int port;
    private String userName;
    public NetworkedBridgeConnection(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
    }

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
    public void getEmulatorConnection(Context context, final OnResponse callback) {
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
    public void getOn(Context context, int id, LightsAPIManager.myCallbackThingy myCallbackThingy) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "on", myCallbackThingy);
    }

    @Override
    public void getHue(Context context, int id, LightsAPIManager.myCallbackThingy myCallbackThingy) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "hue", myCallbackThingy);
    }

    @Override
    public void getBri(Context context, int id, LightsAPIManager.myCallbackThingy myCallbackThingy) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "bri", myCallbackThingy);
    }

    @Override
    public void getSat(Context context, int id, LightsAPIManager.myCallbackThingy myCallbackThingy) {
        LightsAPIManager.getInstance(context).getFromState(ipAdress, port, userName, id, "sat", myCallbackThingy);
    }

    @Override
    public void getIds(Context context, LightsAPIManager.idCallback callback) {
        LightsAPIManager.getInstance(context).getId(ipAdress, port, userName, callback);
    }


}