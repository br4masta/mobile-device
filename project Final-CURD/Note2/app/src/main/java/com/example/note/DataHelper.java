package com.example.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "note.db"; private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//TODO Auto-generade method stub
        String sql = "create table note(no integer primary key,nama text null,tgl text null,catatan text null);";
        Log.d("Data","onCreate: " + sql); db.execSQL(sql);
        sql = "INSERT INTO note (no,nama,tgl,catatan) VALUES ('1001','Brian Aldy','14-02-1996','pppppp');"; db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1,int arg2) {
//TODO Auto-generated method stub
    }
}
