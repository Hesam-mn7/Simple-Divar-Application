package com.example.divarhesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divarhesam.DbHelper.UserDbHelper;

public class MainActivityChatDivar extends AppCompatActivity {

    Button btagahi;
    Button btsabt;
    Button btdasteha;
    Button btdivareman;
    ImageView imgback;
    TextView tvtitle;
    boolean cheekUser = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat_divar);

        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("چت دیوار");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        init();

        btagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChatDivar.this , MainActivity.class);
                startActivity(intent);
            }
        });
        btsabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChatDivar.this , MainActivitySabtAgahi.class);
                startActivity(intent);
            }
        });
        btdasteha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChatDivar.this , MainActivityDaste.class);
                startActivity(intent);
            }
        });
        btdivareman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheekUser = new UserDbHelper(MainActivityChatDivar.this).checkEmptyDataBase();
                if(cheekUser){
                    Intent intent = new Intent(MainActivityChatDivar.this , MainActivityHesabKarbari.class);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(MainActivityChatDivar.this , MainActivityVorod.class);
                    startActivity(intent);
                }
            }
        });

    }
    private void init(){
        btagahi = findViewById(R.id.btagahi);
        btsabt = findViewById(R.id.btsabt);
        btdasteha = findViewById(R.id.btdasteha);
        btdivareman = findViewById(R.id.btdivareman);
    }
}