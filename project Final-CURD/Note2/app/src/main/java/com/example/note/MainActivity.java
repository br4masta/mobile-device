package com.example.note;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity { String[] daftar;
    ListView ListView01; Menu menu;
    protected Cursor cursor; DataHelper dbcenter;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        Button btn=(Button)findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View arg0) {
            Intent inte = new Intent(MainActivity.this, TambahNote.class); Log.d("11", "kudune ora error");
            startActivity(inte);
        }
        });

        ma = this;
        dbcenter = new DataHelper(this); RefreshList();
    }
    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM note", null);
        daftar = new String[cursor.getCount()]; cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView) findViewById(R.id.listView);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, daftar));
        ListView01.setSelected(true);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() { @Override
        public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3)
        {
            final String selection = daftar[arg2];
            final CharSequence[] dialogitem = {"Lihat note", "Update note",
                    "Hapus note"};
            AlertDialog.Builder builder = new
                    AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Pilihan");
            builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    switch (item) {
                        case 0:
                            Intent i = new Intent(getApplicationContext(),LihatNote.class);
                            i.	putExtra("nama", selection); startActivity(i);
                            break;


                        case 1:
                            Intent in = new Intent(getApplicationContext(),UpdateNote.class);

                            in.putExtra("nama", selection);
                            startActivity(in);
                            break;
                        case 2:
                            SQLiteDatabase db = dbcenter.getWritableDatabase();
                            db.execSQL("delete from note where nama = '" +  selection + "'");

                            RefreshList();
                            break;


                    }
                }





            });
            builder.create().show();
        }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}