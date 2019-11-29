package com.example.huedroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.huedroid.Lamp;
import com.example.huedroid.R;

public class ColorControl extends AppCompatActivity {
    private Lamp lamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_control);
        this.lamp = (Lamp)getIntent().getParcelableExtra("lamp");

        findViewById(R.id.btnLightOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamp.setStateOn(false);
                lamp.getBridgeConnection().setOn(getBaseContext(), false, lamp.getId());
            }
        });

        findViewById(R.id.btnLightOn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamp.setStateOn(true);
                lamp.getBridgeConnection().setOn(getBaseContext(), true, lamp.getId());
            }
        });
    }
}
