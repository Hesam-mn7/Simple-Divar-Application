package com.example.divarhesam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.example.divarhesam.adapter.CustomAdaptor2;
import com.example.divarhesam.entity.Proudact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivityDetails extends AppCompatActivity {
    ImageView imgdetail;
    TextView tvnamedetail;
    TextView tvtimedetail;
    TextView tvvaluedetail;
    TextView tvdetails;
    Button btnchat;
    Button btncall;
    ListView listview_rahnamagozaresh;
    ImageView imgback;
    ImageView imgedit;
    ImageView imgdelete , imgshare;
    TextView tvtitlename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);
        getSupportActionBar().hide();


        init();
        initValue();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //create listview
        listview_rahnamagozaresh = findViewById(R.id.listview_rahnamagozaresh);

        //create data
        ArrayList<Rahnmagozaresh> data = database();

        //create adaptor
        CustomAdaptor2 customAdaptor2 = new CustomAdaptor2(this, data);

        //bind
        listview_rahnamagozaresh.setAdapter(customAdaptor2);

        //click
        listview_rahnamagozaresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivityDetails.this, MainActivityRahnama.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivityDetails.this, MainActivityGozareshMoshkel.class);
                    startActivity(intent);
                }

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        initValue();
    }


    private void initValue(){
        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        final int imgId = intent.getIntExtra("imgId",0);
        final String name = intent.getStringExtra("name");
        final String value = intent.getStringExtra("value");
        final String time = intent.getStringExtra("time");
        final String details = intent.getStringExtra("details");
        final String numberPhone = intent.getStringExtra("numberPhone");
        final int id = intent.getIntExtra("id", 0);

        tvtitlename.setText(name);

        imgdetail.setImageResource(imgId);

        tvnamedetail.setText(name);
        tvvaluedetail.setText(value);
        tvtimedetail.setText(time);
        tvdetails.setText(details);

        if (imgId == R.drawable.nophoto){
            imgdelete.setVisibility(View.VISIBLE);
            imgedit.setVisibility(View.VISIBLE);
            imgshare.setVisibility(View.INVISIBLE);
        }
        else {
            imgdelete.setVisibility(View.INVISIBLE);
            imgedit.setVisibility(View.INVISIBLE);
            imgshare.setVisibility(View.VISIBLE);
            imgdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(MainActivityDetails.this, MainActivityPic.class);
                    intent1.putExtra("imgId", imgId);
                    startActivity(intent1);
                }
            });
        }


        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog;
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityDetails.this);
                builder.setTitle("آیا مطمئنید که آگهی حذف شود ؟");
                builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Proudact proudact = new Proudact(name, value, time, imgId, details, numberPhone);
                        int result = new ProudactDbHelper(MainActivityDetails.this).delete(proudact);

                        if (result > 0) {
                            finish();
                            Toast.makeText(MainActivityDetails.this, "آگهی شما با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivityDetails.this, "error", Toast.LENGTH_SHORT).show();
                        }

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

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivityDetails.this, MainActivityEdit.class);
                intent1.putExtra("name", name);
                intent1.putExtra("value", value);
                intent1.putExtra("time", time);
                intent1.putExtra("imgId", R.drawable.ic_launcher_background);
                intent1.putExtra("details", details);
                intent1.putExtra("numberPhone", numberPhone);
                intent1.putExtra("id", id);
                startActivity(intent1);

            }
        });
        imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"اپلیکیشن دیوار حسام"+ "\n" +
                        " عنوان آگهی : " + name  + "\n" +
                        " زمان آگهی : " + time  + "\n" +
                        " قیمت : " + value
                );
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });



        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("tel:+" + numberPhone));
                startActivity(intent1);
            }
        });
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("sms:+" + numberPhone));
                startActivity(intent1);
            }
        });

    }

    private ArrayList<Rahnmagozaresh> database() {
        ArrayList<Rahnmagozaresh> result = new ArrayList<>();
        result.add(new Rahnmagozaresh("راهنمای خرید امن", R.drawable.ic_outline_help_24, R.drawable.back_ios));
        result.add(new Rahnmagozaresh("گزارش مشکل آگهی", R.drawable.error, R.drawable.back_ios));
        return result;

    }
    private void init() {
        imgback = findViewById(R.id.imgback);
        imgedit = findViewById(R.id.imgedit);
        imgdelete = findViewById(R.id.imgdelete);
        tvtitlename = findViewById(R.id.tvtitlename);
        imgshare = findViewById(R.id.imgshare);

        imgdetail = findViewById(R.id.imgdetail);
        tvnamedetail = findViewById(R.id.tvnamedetail);
        tvtimedetail = findViewById(R.id.tvtimedetail);
        tvvaluedetail = findViewById(R.id.tvvaluedetail);
        tvdetails = findViewById(R.id.tvdetails);
        btncall = findViewById(R.id.btncall);
        btnchat = findViewById(R.id.btnchat);

    }

}