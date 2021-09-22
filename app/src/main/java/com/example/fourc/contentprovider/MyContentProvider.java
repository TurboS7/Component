package com.example.fourc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.fourc.dbutil.MyDBHelper;

public class MyContentProvider extends ContentProvider {
    private MyDBHelper dbHelper;
    private static final String AUTHORITIES = "com.example.fourc";
    private static final String TABLE_NAME = "person";
    /*用来存放所有合法的URI的容器*/
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    /*保存合法的URI*/
    /*content://com.example.fourc/person  不根据id来操作*/
    /*content://com.example.fourc/person/2  根据id来操作*/
    static {
        matcher.addURI(AUTHORITIES,"/person",0);
        matcher.addURI(AUTHORITIES,"/person/#",1);//匹配任意的数字
    }

    public MyContentProvider() {
    }
    @Override
    public boolean onCreate() {
        dbHelper = new MyDBHelper(getContext());
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        switch (matcher.match(uri)){
            case 0:
                Cursor cursor = database.query(TABLE_NAME,projection,selection,null,null,null);
                return cursor;
                break;
            case 1:
                long id = ContentUris.parseId(uri);
                Cursor cursor1 = database.query(TABLE_NAME,projection,"id=?",new String[]{String.valueOf(id)},null, null, null);
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}