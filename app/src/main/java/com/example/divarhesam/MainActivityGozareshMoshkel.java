package com.example.divarhesam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityGozareshMoshkel extends AppCompatActivity {
    ImageView imgback;
    TextView tvtitle;
    Button btnenseraf;
    Button btntaiid;
    RadioGroup rggozaresh;
    boolean mohtava , ax , etelaat , gheymat , kolahbardari , address , moshkel , namojod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gozaresh_moshkel);

        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("گزارش مشکل آگهی");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnenseraf = findViewById(R.id.btnenseraf);
        btntaiid = findViewById(R.id.btntaiid);

        btnenseraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rggozaresh = findViewById(R.id.rggozaresh);
        rggozaresh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbmohtava){
                    mohtava = true ;
                }
                else if(checkedId == R.id.rbax){
                    ax = true ;
                }
                else if (checkedId == R.id.rbetelaat){
                    etelaat = true;
                }
                else if (checkedId == R.id.rbgheymat){
                    gheymat = true;
                }
                else if (checkedId == R.id.rbkolahbardari){
                    kolahbardari = true;
                }
                else if (checkedId == R.id.rbaddress){
                    address = true;
                }
                else if (checkedId == R.id.rbmoshkel){
                    moshkel = true;
                }
                else if(checkedId == R.id.rbnamojod){
                    namojod = true;
                }
            }
        });

        btntaiid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityGozareshMoshkel.this);
                builder.setCancelable(false);
                builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                if (mohtava){
                    builder.setMessage("مشکل شما در خصوص محتوای آگهی ثبت شد.");
                    mohtava=false;
                }
                else if (ax){
                    builder.setMessage("مشکل شما در خصوص عکس آگهی ثبت شد.");
                    ax = false;
                }
                else if (etelaat){
                    builder.setMessage("مشکل شما در خصوص اطلاعات تماس ثبت شد.");
                    etelaat = false;
                }
                else if (gheymat){
                    builder.setMessage("مشکل شما در خصوص قیمت ثبت شد.");
                    gheymat = false;
                }
                else if (kolahbardari){
                    builder.setMessage("مشکل شما در خصوص کلاه برداری ثبت شد.");
                    kolahbardari = false;
                }
                else if (address){
                    builder.setMessage("مشکل شما در خصوص آدرس و نقشه ثبت شد.");
                    address = false;
                }
                else if (moshkel){
                    builder.setMessage("مشکل شما در خصوص مشکل با صاحب آگهی ثبت شد.");
                    moshkel = false;
                }
                else if (namojod){
                    builder.setMessage("مشکل شما در خصوص ناموجود بودن در مورد آگهی ثبت شد.");
                    namojod = false;
                }
                else{
                    builder.setMessage("یک مشکل را انتخاب کنید!");
                    builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setCancelable(true);
                }
                alertDialog = builder.create();
                alertDialog.show();

            }
        });


    }
}