package com.example.huedroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huedroid.Connection.BridgeConnection;
import com.example.huedroid.Connection.BridgeFactory;
import com.example.huedroid.Connection.LightsAPIManager;
import com.example.huedroid.Connection.NetworkedBridgeConnection;
import com.example.huedroid.ui.MainScreen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btn3_click();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.startActivity(new Intent(this, MainScreen.class));
    }

    public void btn3_click() throws Exception{
        final Context context = this;
        BridgeFactory.getEmulatorConnection(
                "10.0.2.2",
                80,
                this,
                new BridgeFactory.OnBridgeConnection() {
                    @Override
                    public void Response(BridgeConnection connection) {
                        String username = ((NetworkedBridgeConnection)connection).getUserName();
                        ((NetworkedBridgeConnection)connection).getOn(context, 2, new LightsAPIManager.myCallbackThingy() {
                            @Override
                            public void respond(String mytext) {
                                ((TextView)findViewById(R.id.letext)).setText(mytext);
                            }
                        });
                    }
                });
    }
}
