package com.example.fourc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcontent_provider);

    }
}