package com.example.divarhesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityDarbareMa extends AppCompatActivity {

    ImageView imgback;
    TextView tvtitle ;
    Button btagahi;
    Button btnsabtagahi;
    Button btchat;
    Button btdasteha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_darbare_ma);
        init();

        navigation();

        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("درباره ما");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void navigation(){

        btagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbareMa.this , MainActivity.class);
                startActivity(intent);
            }
        });

        btnsabtagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbareMa.this , MainActivitySabtAgahi.class);
                startActivity(intent);
            }
        });

        btchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbareMa.this , MainActivityChatDivar.class);
                startActivity(intent);
            }
        });
        btdasteha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbareMa.this , MainActivityDaste.class);
                startActivity(intent);
            }
        });


    }
    private void init(){
        btnsabtagahi = findViewById(R.id.btnsabtagahi);
        btagahi = findViewById(R.id.btagahi);
        btchat = findViewById(R.id.btchat);
        btdasteha = findViewById(R.id.btdasteha);
    }
}