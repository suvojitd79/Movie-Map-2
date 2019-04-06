package com.example.moviemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.ListPreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.moviemap.databinding.ActivityPreferencesBinding;

public class Preferences extends AppCompatActivity{
    ActivityPreferencesBinding activityPreferencesBinding;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activityPreferencesBinding = DataBindingUtil.setContentView(this,R.layout.activity_preferences);
        setContentView(activityPreferencesBinding.getRoot());
        toolbar = (Toolbar) activityPreferencesBinding.toolBar;
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Preferences.this,MainActivity.class));
                finish();
            }
        });


    }




}
