package com.e.poissyflore;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //@BindView(R.id.activity_main_bottom_navigation) BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_frame_layout,new HomeFragment())
                .commit();
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        {
            switch (item.getItemId()) {
                case R.id.action_sensors:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_main_frame_layout,new DashboardFragment())
                            .commit();
                    break;
                case R.id.action_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_main_frame_layout,new HomeFragment())
                            .commit();
                    break;
                case R.id.action_infos:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_main_frame_layout,new InfosFragment())
                            .commit();
                    break;
            }

            return true;
        }

    }
}