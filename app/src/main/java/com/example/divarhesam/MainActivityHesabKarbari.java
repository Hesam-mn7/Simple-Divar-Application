package com.example.divarhesam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divarhesam.DbHelper.ProudactDbHelper;
import com.example.divarhesam.DbHelper.UserDbHelper;
import com.example.divarhesam.adapter.CustomAdapter;
import com.example.divarhesam.entity.Proudact;

import java.util.ArrayList;

public class MainActivityHesabKarbari extends AppCompatActivity {

    Button btnhazf ;
    Button btagahi;
    Button btnsabtagahi;
    Button btchat;
    Button btdasteha;
    ImageView imgback;
    TextView tvtitle , tozih;
    LinearLayout constShare , constInfo , constMyAgahi , linearlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hesab_karbari);

        init();
        navigation();
        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("دیوار من");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        final String number = intent.getStringExtra("number");


        btnhazf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityHesabKarbari.this);
                builder.setTitle("آیا مایل به خروج از حساب کاربری خود هستید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new UserDbHelper(MainActivityHesabKarbari.this).deleteUser();
                        Intent intent1 = new Intent(MainActivityHesabKarbari.this , MainActivity.class);
                        startActivity(intent1);
                        finish();

                    }
                });
                builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();

            }
        });

        constShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"اپلیکیشن دیوار "+ "\n" +
                        " طراحی شده توسط : حسام الدین موسویون "+ "\n" +
                        " لینک دانلود : " + "https://uupload.ir/view/yyj6_divar.rar/"
                );
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        constInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivityHesabKarbari.this , MainActivityDarbareMa.class);
                startActivity(intent1);
            }
        });

        constMyAgahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHesabKarbari.this , MainActivityMyAgahi.class);
                startActivity(intent);
            }
        });
    }
    private void navigation(){

        btagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHesabKarbari.this , MainActivity.class);
                startActivity(intent);
            }
        });

        btnsabtagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHesabKarbari.this , MainActivitySabtAgahi.class);
                startActivity(intent);
            }
        });

        btchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHesabKarbari.this , MainActivityChatDivar.class);
                startActivity(intent);
            }
        });
        btdasteha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHesabKarbari.this , MainActivityDaste.class);
                startActivity(intent);
            }
        });


    }

    private void init(){
        btnsabtagahi = findViewById(R.id.btnsabtagahi);
        btagahi = findViewById(R.id.btagahi);
        btchat = findViewById(R.id.btchat);
        btdasteha = findViewById(R.id.btdasteha);
        constShare = findViewById(R.id.constShare);
        constInfo = findViewById(R.id.constInfo);
        tozih = findViewById(R.id.tozih);
        btnhazf = findViewById(R.id.btnhazf);
        constMyAgahi = findViewById(R.id.constMyAgahi);
        linearlist = findViewById(R.id.linearlist);

    }
}