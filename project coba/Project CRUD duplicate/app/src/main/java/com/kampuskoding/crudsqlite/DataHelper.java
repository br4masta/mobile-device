package com.kampuskoding.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ujang Wahyu on 28/01/2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERTSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERTSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table biodata(no integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
        Log.d("Data","onCreate: "+sql);
        db.execSQL(sql);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }

}
