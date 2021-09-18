package com.example.fourc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class SystemContentProviderActivity extends AppCompatActivity {
    private EditText et_uname,et_phone,et_number;
    private TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_content_provider);
        /*申请权限*/
        requestPermission();
        initUI();/*初始化UI*/
//        initData();/*初始化数据*/

    }
    /*初始化UI*/
    private void initUI(){
    et_phone = findViewById(R.id.et_phone);
    et_uname = findViewById(R.id.et_uname);
    et_number = findViewById(R.id.et_number);
    tv_info = findViewById(R.id.tv_info);
    }
//    private void initData(){
//        resolver = getContentResolver();/*获取ContentResolver*/
//    }
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
        ContentResolver resolver = getContentResolver();
        String name = et_uname.getText().toString();
        String phone = et_phone.getText().toString();
        /*创建一个空的ContentValues*/
        ContentValues values = new ContentValues();
        /*向ContactsContract.RawContacts.CONTENT_URI中插入一个空值*/
        /*目的是获取系统返回的rawContactId，以便添加联系人名字和电话的时候使用的是同一个id*/
        Uri rawContactsUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long rawContactId = ContentUris.parseId(rawContactsUri);
        /**
         * 1.清空values
         * 2.设置id
         * 3.设置内容类型
         * 4.设置联系人名称
         */
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId);
        values.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
        /*向联系人URI添加联系人姓名*/
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);

        /**
         * 1.清空values
         * 2.设置id
         * 3.设置内容类型
         * 4.设置联系人电话
         * 5.设置电话类型
         */
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,phone);
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        resolver.insert(ContactsContract.Data.CONTENT_URI , values);
        showToast("联系人："+name+"-"+phone+"成功存入通讯录中！");
//        query();
    }
    /*删除数据*/
    public void onDelete(View view) {
        ContentResolver resolver = getContentResolver();
        int number = Integer.valueOf(et_number.getText().toString());
        int result = resolver.delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts._ID + "=?",
                new String[]{String.valueOf(number)});
        System.out.println("number = "+ number);
        System.out.println(result);
//        query();
    }
    /*更新数据*/
    public void onUpdate(View view) {
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, et_uname.getText().toString());
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,et_phone.getText().toString());
        int result = resolver.update(ContactsContract.Data.CONTENT_URI, values,
                ContactsContract.Data.RAW_CONTACT_ID +"=?",new String[]{
                        String.valueOf(et_number.getText().toString())});
//        query();
    }
    /*查询数据*/
    public void onQuery(View view) {
    query();
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
    /*查询通讯录*/
   private void query(){
        /*获取Resolver*/
        ContentResolver resolver = getContentResolver();
        String text = null;
        tv_info.setText("");
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String ID = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts._ID));
            int num = Integer.valueOf(ID)/2;
            System.out.println("ID"+ID);
            text  = text+"用户名："+name + "电话号码："+number+"[ID:"+num+"]" +"\n" ;
            tv_info.setText(text);
        }
        cursor.close();
    }

}