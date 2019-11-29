package com.example.huedroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huedroid.Lamp;
import com.example.huedroid.R;
import com.example.huedroid.ui.controls.LightControl;
import com.example.huedroid.ui.ui.main.LightsFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.huedroid.ui.ui.main.SectionsPagerAdapter;

public class MainScreen extends AppCompatActivity implements LightsFragment.OnLightsFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        findViewById(R.id.btnManageConnections).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectionManagerIntent = new Intent(getBaseContext(), ConnectionManager.class);
                startActivity(connectionManagerIntent);
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Lamp item) {
        Intent controlIntent = new Intent(this, ColorControl.class);
        startActivity(controlIntent);
    }
}
