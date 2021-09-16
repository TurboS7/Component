package com.example.fourc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class SystemContentProviderActivity extends AppCompatActivity {
    private  ContentResolver resolver;
    private EditText et_uname,et_phone;
    private TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_content_provider);
        /*申请权限*/
        requestPermission();
        initUI();/*初始化UI*/
        initData();/*初始化数据*/

    }
    /*初始化UI*/
    private void initUI(){
    et_phone = findViewById(R.id.et_upsw);
    et_uname = findViewById(R.id.et_uname);
    tv_info = findViewById(R.id.tv_info);
    }
    private void initData(){
        resolver = getContentResolver();/*获取ContentResolver*/
    }
    /*获取权限*/
    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS}, 0);
            }
        }
    }
    /*插入数据*/
    public void onInsert(View view) {

    }
    /*删除数据*/
    public void onDelete(View view) {

    }
    /*更新数据*/
    public void onUpdate(View view) {

    }
    /*查询数据*/
    public void onQuery(View view) {
        /*获取Resolver*/
        String text = "";
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            text  = text+"用户名："+name + "\n" + "电话号码："+number +"\n";
            tv_info.setText(text);
        }
        cursor.close();
    }
    /*申请权限的回调*/
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            for (int i = 0; i <grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    showToast(permissions[i]+"授权成功！");
                }else {
                    showToast(permissions[i]+"授权失败！");
                }
            }
        }
    }
    /*自定义设置弹窗的方法*/
    private void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


}