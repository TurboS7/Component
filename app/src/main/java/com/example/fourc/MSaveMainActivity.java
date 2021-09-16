package com.example.fourc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/*
* */
public class MSaveMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msave_main);
    }

    public void toSharedPreferences(View view) {
        startActivity(new Intent(this,MSharedPreferencesActivity.class));
    }

    public void toSQLite(View view) {
        startActivity(new Intent(this,MSQLiteActivity.class));
    }
}