package com.example.fourc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*跳转到四大组件*/
    public void toFourC(View view) {
        intent = new Intent(getApplication() , MFourCActivity.class);
        startActivity(intent);
    }
    /*存储*/
    public void toSave(View view) {
        intent = new Intent(getApplication(),MSaveMainActivity.class);
        startActivity(intent);
    }


}