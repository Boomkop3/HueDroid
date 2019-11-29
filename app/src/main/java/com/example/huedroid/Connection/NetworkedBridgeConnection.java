package com.example.huedroid.Connection;

import android.content.Context;

import com.example.huedroid.OnResponse;

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
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, "f0235573166c953eee744854fec4577", id, "on", on);
    }

    @Override
    public void setHue(Context context, int hue, int id) {
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, "f0235573166c953eee744854fec4577", id, "hue", hue);
    }

    @Override
    public void setBri(Context context, int bri, int id) {
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, "f0235573166c953eee744854fec4577", id, "bri", bri);
    }

    @Override
    public void setSat(Context context, int sat, int id) {
        LightsAPIManager.getInstance(context).sendToState(ipAdress, port, "f0235573166c953eee744854fec4577", id, "sat", sat);
    }

    @Override
    public boolean getOn(Context context, int id) {
        return false;
    }

    @Override
    public int getHue(Context context, int id) {
        return 0;
    }

    @Override
    public int getBri(Context context, int id) {
        return 0;
    }

    @Override
    public int getSat(Context context, int id) {
        return 0;
    }

    @Override
    public int getId(Context context, int id) {
        return 0;
    }


}