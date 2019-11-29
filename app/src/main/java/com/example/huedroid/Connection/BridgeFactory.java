package com.example.huedroid.Connection;

import android.content.Context;

import com.example.huedroid.OnResponse;

public class BridgeFactory{
    public static void getHueConnection(String ipAdress, int port, String username, final OnBridgeConnection callback){
        final NetworkedBridgeConnection connection = new NetworkedBridgeConnection(ipAdress, port);
        connection.setUserName(username);
        callback.Response(connection);
    }
    public interface OnBridgeConnection {
        public void Response(BridgeConnection connection);
    }
}


