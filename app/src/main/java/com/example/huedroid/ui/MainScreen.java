package com.example.huedroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huedroid.Connection.BridgeConnection;
import com.example.huedroid.Connection.BridgeFactory;
import com.example.huedroid.Connection.LightsAPIManager;
import com.example.huedroid.Lamp;
import com.example.huedroid.R;
import com.example.huedroid.storage.SQLiteStorage;
import com.example.huedroid.storage.StorageManager;
import com.example.huedroid.ui.controls.LightControl;
import com.example.huedroid.ui.ui.main.LightsFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.huedroid.ui.ui.main.SectionsPagerAdapter;

import java.sql.Connection;
import java.util.ArrayList;

public class MainScreen extends AppCompatActivity implements LightsFragment.OnLightsFragmentInteractionListener {
    private ArrayList<Lamp> lampen = new ArrayList<>();
    private SectionsPagerAdapter viewAdapter;

    public ArrayList<Lamp> getLamps(){
        return lampen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewAdapter = sectionsPagerAdapter;
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        this.onLightRefresh();

        findViewById(R.id.btnManageConnections).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectionManagerIntent = new Intent(getBaseContext(), ConnectionManager.class);
                startActivity(connectionManagerIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onLightRefresh();
    }

    @Override
    public void onListFragmentInteraction(Lamp lamp) {
        Intent controlIntent = new Intent(this, ColorControl.class);
        controlIntent.putExtra("lamp", lamp);
        startActivity(controlIntent);
    }

    public void onNewLightAvailable(Lamp lamp) {
        this.lampen.add(lamp);
        viewAdapter.notifyDataSetChanged();
    }

    public void onLightRefresh() {
        this.lampen.clear();
        try {
            SQLiteStorage.dbConnectionResponse connection = StorageManager.getDetaultStorage(this).getCurrentConnection();
            BridgeFactory.getHueConnection(connection.ip, connection.port, connection.session, new BridgeFactory.OnBridgeConnection() {
                @Override
                public void Response(BridgeConnection connection) {
                    connection.getLights(getApplicationContext(), new LightsAPIManager.APIListener() {
                        @Override
                        public void onLightAvailable(Lamp lamp) {
                            onNewLightAvailable(lamp);
                        }

                        @Override
                        public void onLightError(Error error) {
                            error.printStackTrace();
                        }

                        @Override
                        public void onLightRefresh() {
                            // ?
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
