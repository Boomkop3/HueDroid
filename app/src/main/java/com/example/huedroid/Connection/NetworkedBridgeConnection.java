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
}