package com.example.divarhesam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityVorodCode extends AppCompatActivity {

    EditText etvorod;
    Button btnvorod , btndarkhast;
    TextView tozihat;
    
    ImageView imgback;
    TextView tvtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vorod_code);
        
        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("ورود به حساب کاربری");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        init();

        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        final String number = intent.getStringExtra("number");
        tozihat.setText(" لطفا کد تاییدی که به شماره "+number+" ارسال شده است را وارد کنید. ");
        
        btndarkhast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivityVorodCode.this, "درخواست کد برای شما ارسال شد", Toast.LENGTH_SHORT).show();
            }
        });
        btnvorod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code =etvorod.getText().toString();
                if(code.equals("557324")){
                    Intent intent1 = new Intent(MainActivityVorodCode.this , MainActivityHesabKarbari.class);
                    intent1.putExtra("number",number);
                    startActivity(intent1);
                    finish();
                    Toast.makeText(MainActivityVorodCode.this, "شما وارد حساب کاربری خود شدید.", Toast.LENGTH_SHORT).show();
                    
                }else {
                    AlertDialog alertDialogl;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityVorodCode.this);
                    builder.setTitle("خطا");
                    builder.setIcon(R.drawable.ic_baseline_error_24);
                    builder.setMessage("کد تایید معتبر نیست.");
                    builder.setCancelable(true);
                    builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialogl = builder.create();
                    alertDialogl.show();
                }
            }
        });
        
    }
    private void init(){
        etvorod = findViewById(R.id.etvorod);
        btnvorod = findViewById(R.id.btnvorod);
        btndarkhast = findViewById(R.id.btndarkhast);
        tozihat = findViewById(R.id.tozihat);


    }
}