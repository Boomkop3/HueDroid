package com.example.huedroid.Connection;

import android.content.Context;
import com.android.volley.toolbox.Volley;
import com.example.huedroid.OnResponse;

public class BridgeFactory{
    public static void getEmulatorConnection(String ipAdress, int port, Context context, final OnBridgeConnection callback){
        final NetworkedBridgeConnection connection = new NetworkedBridgeConnection(ipAdress, port);
        connection.getEmulatorConnection(context, new OnResponse() {
            @Override
            public void response() {
                callback.Response(connection);
            }
        });
    }
    public static BridgeConnection getWifiConnection(String ipAdress, OnBridgeConnection callback){
        NetworkedBridgeConnection connection = new NetworkedBridgeConnection(ipAdress, 80);
        return connection;
    }
    public static BridgeConnection getLocalAvansConnectionA(OnBridgeConnection callback){
        NetworkedBridgeConnection connection = new NetworkedBridgeConnection("192.168.1.179", 80);
        connection.setUserName("SR9GSJKwoECrCeqiCTRoMCToNYfphHD6Yiu2u8me");
        return connection;
    }
    public static BridgeConnection getLocalAvansConnectionB(OnBridgeConnection callback){
        NetworkedBridgeConnection connection = new NetworkedBridgeConnection("145.48.205.33", 80);
        connection.setUserName("iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB");
        return connection;
    }
    public interface OnBridgeConnection {
        public void Response(BridgeConnection connection);
    }
}


