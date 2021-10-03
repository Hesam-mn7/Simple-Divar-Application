package com.example.divarhesam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
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

import com.example.divarhesam.DbHelper.UserDbHelper;
import com.example.divarhesam.entity.User;

public class MainActivityVorod extends AppCompatActivity {

    EditText etvorod;
    Button btnnext;

    ImageView imgback;
    TextView tvtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityvorod);

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


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number =etvorod.getText().toString();

                User user = new User(number);
                long result = new UserDbHelper(MainActivityVorod.this).insert(user);

                if (result>-1){
                    if( number.length()==11 && number.startsWith("0") || number.length()==10 && !number.startsWith("0") ){
                        Intent intent = new Intent(MainActivityVorod.this,MainActivityVorodCode.class);
                        intent.putExtra("number",number);
                        startActivity(intent);

                        //notification
                        NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivityVorod.this);
                        builder.setContentTitle("کد تایید دیوار:");
                        builder.setContentText("557324");
                        builder.setSmallIcon(R.drawable.divar);

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            builder.setChannelId("chanelId");
                            NotificationChannel channel = new NotificationChannel("chanelId", "name", NotificationManager.IMPORTANCE_DEFAULT);
                            manager.createNotificationChannel(channel);
                        }

                        Notification notification = builder.build();
                        manager.notify(1,notification);

//                        Toast.makeText(MainActivityVorod.this,""+ result, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        AlertDialog alertDialogl;
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityVorod.this);
                        builder.setTitle("خطا");
                        builder.setIcon(R.drawable.ic_baseline_error_24);
                        builder.setMessage("شماره موبایل نامعتبر است.");
                        builder.setCancelable(true);
                        builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialogl = builder.create();
                        alertDialogl.show();

                    }

                }else {
                    Toast.makeText(MainActivityVorod.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void init(){
        etvorod = findViewById(R.id.etvorod);
        btnnext = findViewById(R.id.btnnext);

    }
}