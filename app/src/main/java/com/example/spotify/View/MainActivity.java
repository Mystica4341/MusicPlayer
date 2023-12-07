package com.example.spotify.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    BottomNavigationView bottomNavigationView;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        frameLayout = (FrameLayout) findViewById(R.id.FrameFrag);
        loadFragment(new HomeFrag());
        addEvent();
    }

    public void addEvent(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()  {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home){
                    loadFragment(new HomeFrag());
                    return true;
                } else if (id == R.id.nav_search) {
                    loadFragment(new TimKiemFrag());
                    return true;
                } else if (id == R.id.nav_profile) {
                    loadFragment(new ProfileFrag());
                    return true;
                } else if (id == R.id.nav_playlist) {
                    loadFragment(new ThuVienFrag());
                    return true;
                }
                return false;
            }
        });
    }

    public void loadFragment(androidx.fragment.app.Fragment
                                     fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.FrameFrag,fragment);
        ft.commit();
    }
}