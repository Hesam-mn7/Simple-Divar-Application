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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divarhesam.DbHelper.ProudactDbHelper;
import com.example.divarhesam.DbHelper.UserDbHelper;
import com.example.divarhesam.adapter.CustomAdapter;
import com.example.divarhesam.entity.Proudact;

import java.util.ArrayList;

public class MainActivityMyAgahi extends AppCompatActivity {

    ImageView imgback;
    TextView tvtitle;
    Button btnhazf ;
    Button btagahi;
    Button btnsabtagahi;
    Button btchat;
    Button btdasteha;
    ListView myListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_my_agahi);

        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("آگهی های من");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        init();
        navigation();
        initListView();

        btnhazf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityMyAgahi.this);
                builder.setTitle("آیا مایل به خروج از حساب کاربری خود هستید؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new UserDbHelper(MainActivityMyAgahi.this).deleteUser();
                        Intent intent1 = new Intent(MainActivityMyAgahi.this , MainActivity.class);
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        initListView();
    }

    private void navigation(){

        btagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMyAgahi.this , MainActivity.class);
                startActivity(intent);
            }
        });

        btnsabtagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMyAgahi.this , MainActivitySabtAgahi.class);
                startActivity(intent);
            }
        });

        btchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMyAgahi.this , MainActivityChatDivar.class);
                startActivity(intent);
            }
        });
        btdasteha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMyAgahi.this , MainActivityDaste.class);
                startActivity(intent);
            }
        });


    }

    private void initListView(){
        //create listview
        myListview = findViewById(R.id.myListview);

        //create data
        ArrayList<Proudact> data = new ProudactDbHelper(this).selectMyAgahi();

        //create adaptor
        final CustomAdapter customAdapter = new CustomAdapter(this , data);

        //bind
        myListview.setAdapter(customAdapter);

        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Proudact proudact = (Proudact) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivityMyAgahi.this, MainActivityDetails.class);
                intent.putExtra("name",proudact.getName());
                intent.putExtra("value",proudact.getValue());
                intent.putExtra("time",proudact.getTime());
                intent.putExtra("imgId",proudact.getImgId());
                intent.putExtra("details", proudact.getDetails());
                intent.putExtra("numberPhone", proudact.getNumberPhone());

                startActivity(intent);
            }
        });

    }
    private void init(){
        btnsabtagahi = findViewById(R.id.btnsabtagahi);
        btagahi = findViewById(R.id.btagahi);
        btchat = findViewById(R.id.btchat);
        btdasteha = findViewById(R.id.btdasteha);
        btnhazf = findViewById(R.id.btnhazf);


    }
}