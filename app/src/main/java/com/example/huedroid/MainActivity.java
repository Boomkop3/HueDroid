package com.example.huedroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huedroid.Connection.BridgeConnection;
import com.example.huedroid.Connection.BridgeFactory;
import com.example.huedroid.Connection.NetworkedBridgeConnection;

import static com.example.huedroid.Connection.BridgeFactory.getEmulatorConnection;

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
    }

    public void btn3_click() throws Exception{
        BridgeFactory.getEmulatorConnection(
                "10.0.2.2",
                80,
                this,
                new BridgeFactory.OnBridgeConnection() {
                    @Override
                    public void Response(BridgeConnection connection) {
                        String username = ((NetworkedBridgeConnection)connection).getUserName();
                        ((TextView)findViewById(R.id.letext)).setText(username);
                    }
                });
    }
}
