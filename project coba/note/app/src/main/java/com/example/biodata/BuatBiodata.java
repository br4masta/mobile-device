package com.example.biodata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuatBiodata extends AppCompatActivity {
    protected Cursor cursor; DataHelper dbHelper; Button bt1,bt2;
    EditText text1,text2,text3,text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_buat_biodata); dbHelper = new DataHelper(this); text1=(EditText) findViewById(R.id.editText1); text2=(EditText) findViewById(R.id.editText2); text3=(EditText) findViewById(R.id.editText3); text4=(EditText) findViewById(R.id.editText4); bt1=(Button) findViewById(R.id.button1); bt2=(Button) findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
//TODO Auto-generated method stub
            SQLiteDatabase db = dbHelper.getWritableDatabase(); db.execSQL("insert into note(no,nama,tgl,catatan) values('" +
                    text1.getText().toString()+"','"+
                    text2.getText().toString()+"','" +
                    text3.getText().toString()+"','"+
                    text4.getText().toString()+"')"); Toast.makeText(getApplicationContext(),"Berhasil",
                    Toast.LENGTH_LONG).show();
            MainActivity.ma.RefreshList(); finish();
        }
        });
        bt2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
//TODO Auto-generated method stub
            finish();
        }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
//Inflate the menu: this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

