package com.example.fourc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MFourCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_c);
    }

    public void toServiceActivity(View view) {
        startActivity(new Intent(getApplication() , MServiceActivity.class));
    }

    public void toBroadcastReceiverActivity(View view) {
        startActivity(new Intent(getApplication() , MBroadcastReceiverActivity.class));
    }

    public void toContentProviderActivity(View view) {
        startActivity(new Intent(getApplication() , MContentProviderActivity.class));
    }
}