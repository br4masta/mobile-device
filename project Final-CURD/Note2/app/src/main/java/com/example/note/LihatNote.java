package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatNote extends AppCompatActivity {
    protected Cursor cursor; DataHelper dbHelper; Button bt2;
    TextView lihatcatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lihat_note);
    dbHelper = new DataHelper(this);
        lihatcatatan = (TextView) findViewById(R.id.lihatcatatan);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = ((SQLiteDatabase) db).rawQuery("SELECT * FROM note WHERE catatan =	'" + getIntent().getStringExtra("catatan") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            lihatcatatan.setText(cursor.getString(1).toString());

        }
        bt2 = (Button) findViewById(R.id.button1);
        bt2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
//TODO Auto-generated method stub
            finish();
        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//Inflate the menu: this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}