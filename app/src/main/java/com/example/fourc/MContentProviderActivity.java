package com.example.fourc;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fourc.dbutil.MyDBHelper;

import java.net.URI;

/**
 * 内容提供者的demo
 *
 * ContentProvider用于保存和获取数据，并使其对所有应用程序可见。
 * 这是不同应用程序间共享数据的唯一方式，因为android没有提供所有应用共同访问的公共存储区。
 *
 *  通过Uri进行查询，返回一个Cursor。
 *  query（Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder）
 *  将一组数据插入到Uri 指定的地方。
 *  insert（Uri url, ContentValues values）
 *  更新Uri指定位置的数据。
 *  update（Uri uri, ContentValues values, String where, String[] selectionArgs）
 *  删除指定Uri并且符合一定条件的数据。
 *  delete（Uri url, String where, String[] selectionArgs）
 *
 * ContentProvider和ContentResolver中用到的Uri
 *       在ContentProvider和ContentResolver当中用到了Uri的形式通常有两种，一种是指定全部数据，另一种是指定某个ID的数据。
 *      我们看下面的例子。
 *           content://contacts/people/  这个Uri指定的就是全部的联系人数据。
 *           content://contacts/people/1 这个Uri指定的是ID为1的联系人的数据。
 *
 *      在上边两个类中用到的Uri一般由3部分组成。
 *          第一部分是："content://" 。
 *          第二部分是要获得数据的一个字符串片段。
 *         最后就是ID（如果没有指定ID，那么表示返回全部）。
 */
public class MContentProviderActivity extends AppCompatActivity {
    private static final String TAG = "MContentProviderActivity：";
    private TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcontent_provider);
        initView();/*初始化UI*/
    }
    public void initView() {
        tv_info = findViewById(R.id.tv_info);
    }
    /*查询*/
    public void onQuery1(View view) {
        /*获取ContentResolver*/
        ContentResolver resolver = getContentResolver();
        /*content://com.example.fourc/person/#*/
//        Uri uri = Uri.parse("content://com.example.fourc/person/20");
//        Cursor cursor = resolver.query(uri, null, null ,null, null);
        /*content://com.example.fourc/person*/
        Uri uri = Uri.parse("content://com.example.fourc/person");
//        Cursor cursor = resolver.query(uri, null, "_id=?" , new String[]{20+""}, null);
        Cursor cursor = resolver.query(uri, null, null , null, null);

        String txt = "";
        while (cursor.moveToNext()){
            int _id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String text = cursor.getString(3);
            txt = txt +"\n" +
                    "_id = "+_id+"\n"+
                    "name = "+name+"\n"+
                    "age = "+age+"\n"+
                    "text = "+text+"\n";

        }
        tv_info.setText(txt);
    }

    public void onInstert1(View view) {
        /*获取ContentResolver*/
        ContentResolver resolver = getContentResolver();
        /*插入数据*/
        Uri uri = Uri.parse("content://com.example.fourc/person");
        ContentValues values = new ContentValues();
        values.put("name","tim");
        values.put("age",12);
        values.put("info","tim is a boy ");
        Uri uri_res = resolver.insert(uri, values);
        tv_info.setText("插入的Uri为："+uri_res);

    }
    public void onDelete1(View view) {
        ContentResolver resolver = getContentResolver();
        /*删除数据*/
//        Uri uri = Uri.parse("content://com.example.fourc/person");
//        int delete = resolver.delete(uri, "_id=?", new String[]{"20"});
//        int delete = resolver.delete(uri, null , null);

        Uri uri = Uri.parse("content://com.example.fourc/person/21");
        int delete = resolver.delete(uri, null,null);
        if (delete > 0){
            Log.i(TAG, "onDelete1: 删除成功");
        }else {
            Log.i(TAG, "onDelete1: 删除失败");
        }
    }

    public void onUpdate1(View view) {
        /*获取ContentResolver*/
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name","mok");
        values.put("age",21);
        values.put("info","tim is1212 ");
        /*插入数据*/
//        Uri uri = Uri.parse("content://com.example.fourc/person");
//        resolver.update(uri, values,"_id=?",new String[]{"24"});
//        resolver.update(uri, values,null,null);
        Uri uri = Uri.parse("content://com.example.fourc/person/25");
        resolver.update(uri, values,null,null);
    }

}