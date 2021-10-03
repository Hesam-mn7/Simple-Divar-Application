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

import com.example.divarhesam.DbHelper.ProudactDbHelper;
import com.example.divarhesam.entity.Proudact;

public class MainActivityEdit extends AppCompatActivity {

    EditText etname , etvalue , etdetails, ettime , etnumberPhone  ;
    Button btnedit ;
    TextView tvtitle;
    ImageView imgback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);
        init();

        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("ویرایش آگهی");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Intent intent = getIntent();
        if(intent==null){
            finish();
            return;
        }
        else{
             int imgId = intent.getIntExtra("imgId",0);
              String name = intent.getStringExtra("name");
              String value = intent.getStringExtra("value");
              String time = intent.getStringExtra("time");
              String details = intent.getStringExtra("details");
              String numberPhone = intent.getStringExtra("numberPhone");
              int id = intent.getIntExtra("id",0);

            etname.setText(name);
            etvalue.setText(value);
            etdetails.setText(details);
            ettime.setText(time);
            etnumberPhone.setText(numberPhone);


            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name1=etname.getText().toString();
                    String value1=etvalue.getText().toString();
                    String time1=ettime.getText().toString();
                    String details1=etdetails.getText().toString();
                    String numberPhone1=etnumberPhone.getText().toString();

                    if (name1.length()<3){
                        etname.setError("طول عنوان آگهی باید حداقل 3 حرف باشد.");
                    }
                    else if(value1.isEmpty()){
                        etvalue.setError("قیمت را وارد کنید.");
                    }
                    else if(time1.isEmpty()){
                        ettime.setError("نام محله را وارد کنید.");
                    }
                    else if ((numberPhone1.length() != 11) || !numberPhone1.startsWith("0")){
                        etnumberPhone.setError("شماره موبایل را صحیح وارد کنید." + "\n" + "مانند: 09121234567");
                    }
                    else if(details1.length()<10){
                        etdetails.setError("طول جزئیات آگهی باید حداقل 10 حرف باشد.");
                    }
                    else{
                        Proudact proudact = new Proudact(name1,value1,time1, R.drawable.nophoto , details1 , numberPhone1);

                        long result = new ProudactDbHelper(MainActivityEdit.this).update(proudact);
                        if(result>0){

                            AlertDialog alertDialog;
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityEdit.this);
                            builder.setMessage("ویرایش مشخصات آگهی به طور کامل انجام شد.");
                            builder.setCancelable(false);
                            builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent1 = new Intent(MainActivityEdit.this,MainActivity.class);
                                    startActivity(intent1);
                                    finish();

                                }
                            });
                            alertDialog = builder.create();
                            alertDialog.show();

                            return;


                        }else
                        {
                            Toast.makeText(MainActivityEdit.this, "امکان ویرایش شماره تماس وجود ندارد.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

        }

    }

    private void init(){
        etname = findViewById(R.id.etname);
        etvalue = findViewById(R.id.etvalue);
        etdetails = findViewById(R.id.etdetails);
        ettime = findViewById(R.id.ettime);
        etnumberPhone = findViewById(R.id.etnumberPhone);
        btnedit = findViewById(R.id.btnedit);


    }
}