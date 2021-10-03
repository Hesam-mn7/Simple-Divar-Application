package com.example.divarhesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divarhesam.DbHelper.UserDbHelper;

public class MainActivityDaste extends AppCompatActivity {

    ListView listviewdaste;
    Button btagahi;
    Button btsabt;
    Button btchat;
    Button btdivareman;
    ImageView imgback;
    TextView tvtitle;
    boolean cheekUser = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_daste);

        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("انتخاب دسته بندی");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //create list
        listviewdaste=findViewById(R.id.listviewdaste);

        //createdata
        String [] data = getResources().getStringArray(R.array.dastearray);

        //createadaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);

        //bind
        listviewdaste.setAdapter(adapter);

        //click
        listviewdaste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) parent.getItemAtPosition(position);
                Toast.makeText(MainActivityDaste.this,str,Toast.LENGTH_SHORT).show();
            }
        });


        btagahi = findViewById(R.id.btagahi);
        btsabt = findViewById(R.id.btsabt);
        btchat = findViewById(R.id.btchat);
        btdivareman = findViewById(R.id.btdivareman);

        btagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDaste.this , MainActivity.class);
                startActivity(intent);
            }
        });
        btsabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDaste.this , MainActivitySabtAgahi.class);
                startActivity(intent);
            }
        });
        btchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDaste.this , MainActivityChatDivar.class);
                startActivity(intent);
            }
        });
        btdivareman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheekUser = new UserDbHelper(MainActivityDaste.this).checkEmptyDataBase();
                if(cheekUser){
                    Intent intent = new Intent(MainActivityDaste.this , MainActivityHesabKarbari.class);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(MainActivityDaste.this , MainActivityVorod.class);
                    startActivity(intent);
                }
            }
        });

    }
}