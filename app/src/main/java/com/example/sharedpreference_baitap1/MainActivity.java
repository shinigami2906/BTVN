package com.example.sharedpreference_baitap1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword;
    TextView tvInfor;
    Button btRegister, btEnglish, btVietNamese;
    SharedPreferences sharedPreferences;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Language", MODE_PRIVATE);
        chuyenNgonNgu(sharedPreferences.getString("english", ""));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setId();
        dialog = new Dialog(this);
        if (sharedPreferences.getString("english", "") == "") {
            HopThoai();
        }
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                tvInfor.setText(
                        getResources().getString(R.string.txtHello) + " " + name + "\n"
                                + getResources().getString(R.string.hintEmail) + " " + email + "\n"
                                + getResources().getString(R.string.hintPassword) + " " + password
                );
            }
        });
        btEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenNgonNgu("en");
                saveLanguage("en");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btVietNamese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenNgonNgu("vi");
                saveLanguage("vi");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void setId() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvInfor = findViewById(R.id.tvInfo);
        btRegister = findViewById(R.id.btRegister);
        btEnglish = findViewById(R.id.btEnglish);
        btVietNamese = findViewById(R.id.btVietNamese);
    }

    protected void chuyenNgonNgu(String language) {

        if (language == "")
            return;
        Resources res = getResources();
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(locale);
            res.updateConfiguration(config,null);
        }

    }

    protected void saveLanguage(String language) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("english", language);
        editor.commit();
    }

    protected void HopThoai() {
        dialog.setContentView(R.layout.dailog_language);
        dialog.setCanceledOnTouchOutside(false);
        Button btDialogEnglish = dialog.findViewById(R.id.btDialogEnglish);
        Button btDialogVietnamese = dialog.findViewById(R.id.btDialogVietsamese);
        btDialogEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenNgonNgu("en");
                saveLanguage("en");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btDialogVietnamese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenNgonNgu("vi");
                saveLanguage("vi");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }
}