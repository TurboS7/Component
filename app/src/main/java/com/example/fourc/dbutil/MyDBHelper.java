package com.example.fourc.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDBHelper:";
    /*数据库名称*/
    private static final String DATABASE_NAME= "test.db";
    /*数据库版本*/
    private static final int DATABASE_VERSION = 1;
    /*构造器*/
    public MyDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS person" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, " +
                "age INTEGER," +
                " info TEXT)");
        db.execSQL("insert into person (name,age,info) values('小明1',21,'he is a good guly!')");
        db.execSQL("insert into person (name,age,info) values('小明2',21,'he is a good guly!')");
        db.execSQL("insert into person (name,age,info) values('小明3',21,'he is a good guly!')");
        Log.i(TAG, "onCreate: 数据初始化成功 ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
