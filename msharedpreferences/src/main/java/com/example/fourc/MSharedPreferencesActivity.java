package com.example.fourc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MSharedPreferencesActivity extends AppCompatActivity {
    public static final String SETTING_INFOS = "SETTING_Infos"; /*保存的xml的文件名*/
    public static final String SP_RECORD = "SP_RECORD"; /*保存的xml的文件名*/
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";
    private EditText field_name;  //接收用户名的组件
    private EditText filed_pass;  //接收密码的组件
    private EditText et_input;  //输入内容
    private EditText et_get;  //获取内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mshared_preferences);

        //Find VIew
        field_name = (EditText) findViewById(R.id.name);  //首先获取用来输入用户名的组件
        filed_pass = (EditText) findViewById(R.id.password); //同时也需要获取输入密码的组件
        et_input = (EditText) findViewById(R.id.et_input); //同时也需要获取输入密码的组件
        et_get = (EditText) findViewById(R.id.et_get); //同时也需要获取输入密码的组件

        SharedPreferences sp  = getSharedPreferences(SETTING_INFOS,MODE_PRIVATE);
        String name = sp.getString(NAME,"");
        String psw = sp.getString(PASSWORD,"");
        field_name.setText(name);
        System.out.println("获取的name为" + name);
        filed_pass.setText(psw);
        System.out.println("获取的密码为" + psw);


    }
    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences sp = getSharedPreferences(SETTING_INFOS,MODE_PRIVATE);
        sp.edit()
                .putString(NAME,field_name.getText().toString())
                .putString(PASSWORD,filed_pass.getText().toString())
                .commit();
    }

    public void onSave(View view) {
        SharedPreferences sp = getSharedPreferences(SP_RECORD,0);
        sp.edit()
                .putString("data",et_input.getText().toString())
                .commit();
    }

    public void onGet(View view) {
        SharedPreferences sp = getSharedPreferences(SP_RECORD,0);
        et_get.setText(sp.getString("data",""));
    }

    public void onClean(View view) {
        SharedPreferences sp = getSharedPreferences(SP_RECORD,0);
        sp.edit().clear().commit();
    }
}