package com.example.huedroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

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

        SQLiteStorage.dbConnectionResponse connectionResponse = StorageManager.getDetaultStorage(getApplicationContext()).getCurrentConnection();
        tbxIp.setText(connectionResponse.ip);
        tbxPort.setText(connectionResponse.port + "");
        toggleEmulated.setChecked(connectionResponse.emulated);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = tbxIp.getText().toString().trim();
                int port = Integer.valueOf(tbxPort.getText().toString().trim());
                boolean emulated = toggleEmulated.isChecked();
                StorageManager.getDetaultStorage(getApplicationContext()).setCurrentConnection(ip, port, emulated, null);
            }
        });
    }
}
