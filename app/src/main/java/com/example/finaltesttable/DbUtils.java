package com.example.finaltesttable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbUtils extends SQLiteOpenHelper {


    public DbUtils(Context context, String name, SQLiteDatabase.CursorFactory factory,
                       int version) {super(context, "my.db", null, 1); }
    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists Record(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),code Varcher(20),post Varcher(20),phone Varcher(20),sex Varcher(20),birth Varcher(20))");

    }
    //软件版本号发生改变时调用,当前无需使用
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

}
