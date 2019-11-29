package com.example.huedroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.huedroid.Lamp;
import com.example.huedroid.R;

public class ColorControl extends AppCompatActivity {
    private Lamp lamp;
    private ImageView imageView;
    private SeekBar hue;
    private SeekBar bri;
    private SeekBar sat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_control);
        this.lamp = (Lamp)getIntent().getParcelableExtra("lamp");
        this.imageView = findViewById(R.id.imageViewLC);
        this.hue = findViewById(R.id.sliderHue);
        this.hue.setProgress((int)((float)lamp.getStateHue() / (65535.0f / 100.0f)));
        this.bri = findViewById(R.id.sliderBri);
        this.bri.setProgress((int)(((float)lamp.getStateBri() - 1.0f) / (253.0f / 100.0f)));
        this.sat = findViewById(R.id.sliderSat);
        this.sat.setProgress((int)((float)lamp.getStateSat() / (253.0f / 100.0f)));
        if (this.lamp.isStateOn()) {
            this.imageView.setBackgroundColor(this.lamp.getColor());
        }

        this.hue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                lamp.setStateHue((int)((float)hue.getProgress() * (65535.0f / 100.0f)));
                lamp.getBridgeConnection().setHue(getBaseContext(), lamp.getStateHue(), lamp.getId());
                imageView.setBackgroundColor(lamp.getColor());
            }
        });

        this.bri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                lamp.setStateBri((int)((float)bri.getProgress() * (253.0f / 100.0f) + 1.0f));
                lamp.getBridgeConnection().setBri(getBaseContext(), lamp.getStateBri(), lamp.getId());
                imageView.setBackgroundColor(lamp.getColor());
            }
        });

        this.sat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                lamp.setStateSat((int)((float)sat.getProgress() * (254.0f / 100.0f)));
                lamp.getBridgeConnection().setSat(getBaseContext(), lamp.getStateSat(), lamp.getId());
                imageView.setBackgroundColor(lamp.getColor());
            }
        });

        findViewById(R.id.btnLightOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamp.setStateOn(false);
                imageView.setBackgroundColor(0);
                lamp.getBridgeConnection().setOn(getBaseContext(), false, lamp.getId());
            }
        });

        findViewById(R.id.btnLightOn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamp.setStateOn(true);
                imageView.setBackgroundColor(lamp.getColor());
                lamp.getBridgeConnection().setOn(getBaseContext(), true, lamp.getId());
            }
        });
    }
}
