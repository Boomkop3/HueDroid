package com.example.huedroid.Connection;

import android.content.Context;
import com.android.volley.toolbox.Volley;

public class BridgeFactory{
    public static BridgeConnection getEmulatorConnection(String ipAdress, int port, Context context) throws Exception{
        NetworkedBridgeConnection connection = new NetworkedBridgeConnection(ipAdress, port);
        connection.getEmulatorConnection(context);
        return connection;
    }
    public static BridgeConnection getWifiConnection(String ipAdress){
        NetworkedBridgeConnection connection = new NetworkedBridgeConnection(ipAdress, 80);
        return connection;
    }
    public static BridgeConnection getLocalAvansConnectionA(){
        NetworkedBridgeConnection connection = new NetworkedBridgeConnection("192.168.1.179", 80);
        connection.setUserName("SR9GSJKwoECrCeqiCTRoMCToNYfphHD6Yiu2u8me");
        return connection;
    }
    public static BridgeConnection getLocalAvansConnectionB(){
        NetworkedBridgeConnection connection = new NetworkedBridgeConnection("145.48.205.33", 80);
        connection.setUserName("iYrmsQq1wu5FxF9CPqpJCnm1GpPVylKBWDUsNDhB");
        return connection;
    }
}


