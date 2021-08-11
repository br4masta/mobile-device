package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "note_project.db";
    private static final int DATABASE_VERSION = 1;

    public static final String table_name = "table_login";
    public static final String row_id = "_id";
    public static final String row_username = "Username";
    public static final String row_password = "Password";
    private SQLiteDatabase db;

    public DataHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//TODO Auto-generade method stub
        String sql = "create table note(id integer primary key AUTOINCREMENT,catatan text null);";
        Log.d("Data","onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO note (catatan) VALUES ('catatan 1');";
        db.execSQL(sql);

        String sql2 = "create table " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_username + " TEXT," + row_password + " TEXT)";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }
    //Insert Data
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }


    public boolean checkUser(String username, String password){
        String[] columns = {row_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_username + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}
