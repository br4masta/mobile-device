package com.example.note;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText TxUsername, TxPassword;
    Button BtnLogin;

   DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TxUsername = (EditText)findViewById(R.id.txUsername);
        TxPassword = (EditText)findViewById(R.id.txPassword);
        BtnLogin = (Button)findViewById(R.id.btnLogin);

//        dbHelper = new DBHelper(this);
        dbcenter = new DataHelper(this);

        TextView tvCreateAccount = (TextView)findViewById(R.id.tvCreateAccount);

        tvCreateAccount.setText(fromHtml("Belum punya akun. " +
                "</font><font color='#3b5998'>create one</font>"));
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();

                Boolean res = dbcenter.checkUser(username,password);
                if(res == true){
                    Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}

