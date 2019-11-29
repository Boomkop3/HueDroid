package com.example.huedroid.ui;

import android.os.Bundle;

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
    }

    @Override
    public void onListFragmentInteraction(LightControl.LightItem item) {

    }
}
