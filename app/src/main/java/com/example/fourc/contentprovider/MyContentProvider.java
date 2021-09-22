package com.example.fourc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.fourc.dbutil.MyDBHelper;

public class MyContentProvider extends ContentProvider {
    private String TAG = "MyContentProvider:";
    private MyDBHelper dbHelper;
    private static final String AUTHORITIES = "com.example.fourc";
    private static final String TABLE_NAME = "person";
    /*step1:用来存放所有合法的URI的容器*/
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    /*step2:保存合法的URI*/
    /*content://com.example.fourc/person  不根据id来操作*/
    /*content://com.example.fourc/person/2  根据id来操作*/
    static {
        matcher.addURI(AUTHORITIES,"/person",0);
        matcher.addURI(AUTHORITIES,"/person/#",1);//匹配任意的数字
    }

    public MyContentProvider() {
        Log.i(TAG, "MyContentProvider()");
    }
    @Override
    public boolean onCreate() {
        dbHelper = new MyDBHelper(getContext());
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (matcher.match(uri)){
            case 0:
                int d1 = db.delete(TABLE_NAME, selection, selectionArgs);
                return d1;
            case 1:
                long id = ContentUris.parseId(uri);
                int d2 = db.delete(TABLE_NAME, "_id=?", new String[]{id+""});
                return d2;
            default:
                break;
        }
        return 0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       SQLiteDatabase db = dbHelper.getWritableDatabase();
       switch (matcher.match(uri)){
           case 0:
               long number = db.insert(TABLE_NAME, null, values);
               Uri insert_uri = Uri.parse("content://com.example.fourc/person/"+number);
               return insert_uri;
           default:
               return null;
       }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        switch (matcher.match(uri)){
            case 0:
                Cursor cursor = db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,null);
                return cursor;
            case 1:
                long id = ContentUris.parseId(uri);
                Cursor cursor1 = db.query(TABLE_NAME,projection,"_id=?",new String[]{id+""},null,null,null);
                return cursor1;
            default:
                Log.e(TAG, "非法的URI");
                throw new RuntimeException("dangqian");
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        switch (matcher.match(uri)){
            case 0:
                int res = db.update(TABLE_NAME, values, selection, selectionArgs);
                return res;
            case 1:
                long id = ContentUris.parseId(uri);
                int res1 = db.update(TABLE_NAME, values, "_id=?", new String[]{id+""});
                return res1;
        }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}