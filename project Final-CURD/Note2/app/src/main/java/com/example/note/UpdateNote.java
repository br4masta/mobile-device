package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateNote extends AppCompatActivity {
    protected Cursor cursor; DataHelper dbHelper; Button bt1,bt2;
    EditText text4;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    setContentView(R.layout.activiry_update_note);
    dbHelper = new DataHelper(this);

        text4=(EditText) findViewById(R.id.editText4);
        textView = (TextView) findViewById(R.id.tampilkanid);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = ((SQLiteDatabase) db).rawQuery("SELECT * FROM note WHERE catatan = '" + getIntent().getStringExtra("catatan") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) { cursor.moveToPosition(0);
        textView.setText(cursor.getString(0).toString());
        text4.setText(cursor.getString(1).toString());
        }
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
//daftarkan even onClick pada btnSimpan
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
        public void onClick(View arg0) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("update note set catatan='" + text4.getText().toString() + "'  where id ='" + textView.getText().toString() + "'");
            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
            MainActivity.ma.RefreshList(); finish();
        }
        });
        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
        public void onClick(View view) { finish();
        }
        });
    }
    public boolean onCreateOptionMenu(Menu menu) {

//Inflate the menu: this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}