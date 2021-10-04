package com.example.divarhesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divarhesam.DbHelper.ProudactDbHelper;
import com.example.divarhesam.DbHelper.UserDbHelper;
import com.example.divarhesam.adapter.CustomAdapter;
import com.example.divarhesam.entity.Proudact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText etsearch;
    TextView tvloc;
    Button btfilter;
    Button btdaste;
    Button btfroshaparteman;
    Button bttazini;
    Button btdasteha;
    Button btsabt;
    Button btchat;
    Button btdivareman;
    
    boolean cheekUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_layout,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");
        
        //test12

        init();
        initlistview();

        btdaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityDaste.class);
                startActivity(intent);
            }
        });

        btdasteha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivityDaste.class);
                startActivity(intent);
            }
        });
        btsabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , MainActivitySabtAgahi.class);
                startActivity(intent);


            }
        });
        btchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , MainActivityChatDivar.class);
                startActivity(intent);
            }
        });
        btdivareman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cheekUser = new UserDbHelper(MainActivity.this).checkEmptyDataBase();
                if(cheekUser){
                    Intent intent = new Intent(MainActivity.this , MainActivityHesabKarbari.class);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(MainActivity.this , MainActivityVorod.class);
                    startActivity(intent);
                }
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        initlistview();
    }

    private void init(){

        etsearch = findViewById(R.id.etsearch);
        tvloc = findViewById(R.id.tvloc);
        btfilter = findViewById(R.id.btfilter);
        btdaste = findViewById(R.id.btdaste);
        btfroshaparteman = findViewById(R.id.btfroshaparteman);
        bttazini = findViewById(R.id.bttazini);
        btdasteha = findViewById(R.id.btdasteha);
        btsabt = findViewById(R.id.btsabt);
        btchat = findViewById(R.id.btchat);
        btdivareman = findViewById(R.id.btdivareman);

    }

    private void initlistview (){

        //create listview
        listView = findViewById(R.id.listview);

        //create data
        ArrayList<Proudact> data = new ProudactDbHelper(this).select();

        //create adaptor
        final CustomAdapter customAdapter = new CustomAdapter(this , data);

        //bind
        listView.setAdapter(customAdapter);

        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Proudact proudact = (Proudact) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, MainActivityDetails.class);
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

}
