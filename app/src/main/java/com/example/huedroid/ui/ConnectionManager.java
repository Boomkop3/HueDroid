package com.example.huedroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.huedroid.Connection.LightsAPIManager;
import com.example.huedroid.R;
import com.example.huedroid.storage.SQLiteStorage;
import com.example.huedroid.storage.StorageManager;

public class ConnectionManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_manager);

        final TextView tbxIp = findViewById(R.id.tbxIP);
        final TextView tbxPort = findViewById(R.id.tbxPort);
        final Switch toggleEmulated = findViewById(R.id.toggleEmulator);
        final Button btnSave = findViewById(R.id.btnSaveConnection);
        final TextView tbxStatusText = findViewById(R.id.tbxStatusText);
        try {
            SQLiteStorage.dbConnectionResponse connectionResponse = StorageManager.getDetaultStorage(getApplicationContext()).getCurrentConnection();
            tbxIp.setText(connectionResponse.ip);
            tbxPort.setText(connectionResponse.port + "");
            toggleEmulated.setChecked(connectionResponse.emulated);
            tbxStatusText.setText("Connection loaded from storage");
        } catch (Exception ex) { /* whatever */ }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ip = tbxIp.getText().toString().trim();
                final int port = Integer.valueOf(tbxPort.getText().toString().trim());
                final boolean emulated = toggleEmulated.isChecked();

                LightsAPIManager.getInstance(getApplicationContext()).getUsername(ip, port, "smartphone", new LightsAPIManager.OnEmulatorUsername() {
                    @Override
                    public void respond(String username) {
                        StorageManager.getDetaultStorage(getApplicationContext()).setCurrentConnection(ip, port, emulated, username);
                        tbxStatusText.setText("Connection established");
                    }
                });
                tbxStatusText.setText("No connection");
            }
        });
    }
}
