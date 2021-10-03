package com.example.divarhesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divarhesam.DbHelper.ProudactDbHelper;
import com.example.divarhesam.DbHelper.UserDbHelper;
import com.example.divarhesam.entity.Proudact;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Random;

public class MainActivitySabtAgahi extends AppCompatActivity {

    private static final int SELECT_PHOTO = 100;
    EditText etname , etvalue , etdetails, ettime , etnumberPhone  ;
    Button btnsabt ;
    Button btagahi;
    Button btdasteha;
    Button btchat;
    Button btdivareman;
    TextView tvtitle ;
    ImageView tvidax;
    ImageView imgback;
    boolean cheekUser = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sabt_agahi);

        init();

        View actionbar = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);
        getSupportActionBar().setCustomView(actionbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("");

        tvtitle = findViewById(R.id.tvtitle);
        tvtitle.setText("ثبت آگهی");

        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnsabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString();
                String value = etvalue.getText().toString();
                String details = etdetails.getText().toString();
                String time = ettime.getText().toString();
                String numberPhone = etnumberPhone.getText().toString();

                if (name.length()<3){
                    etname.setError("طول عنوان آگهی باید حداقل 3 حرف باشد.");
                }
                else if(value.isEmpty()){
                    etvalue.setError("قیمت را وارد کنید.");
                }
                else if(time.isEmpty()){
                    ettime.setError("نام محله را وارد کنید.");
                }
                else if ((numberPhone.length() != 11) || !numberPhone.startsWith("0")){
                    etnumberPhone.setError("شماره موبایل را صحیح وارد کنید." + "\n" + "مانند: 09121234567");
                }
                else if(details.length()<10){
                    etdetails.setError("طول جزئیات آگهی باید حداقل 10 حرف باشد.");
                }

                else {
                    Proudact proudact = new Proudact(name , value ,time, R.drawable.nophoto , details  , numberPhone);
                    long result = (int) new ProudactDbHelper(MainActivitySabtAgahi.this).insert(proudact);

                    if(result>-1){
                        finish();
                        Toast.makeText(MainActivitySabtAgahi.this, "آگهی شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();

                        return;
                    }else{
                        Toast.makeText(MainActivitySabtAgahi.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

        btagahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySabtAgahi.this , MainActivity.class);
                startActivity(intent);
            }
        });
        btdasteha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySabtAgahi.this , MainActivityDaste.class);
                startActivity(intent);
            }
        });
        btchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySabtAgahi.this , MainActivityChatDivar.class);
                startActivity(intent);
            }
        });
        btdivareman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheekUser = new UserDbHelper(MainActivitySabtAgahi.this).checkEmptyDataBase();
                if(cheekUser){
                    Intent intent = new Intent(MainActivitySabtAgahi.this , MainActivityHesabKarbari.class);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(MainActivitySabtAgahi.this , MainActivityVorod.class);
                    startActivity(intent);
                }
            }
        });
        tvidax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

    }
    private void init(){
        etname = findViewById(R.id.etname);
        etvalue = findViewById(R.id.etvalue);
        etdetails = findViewById(R.id.etdetails);
        ettime = findViewById(R.id.ettime);
        etnumberPhone = findViewById(R.id.etnumberPhone);
        btnsabt = findViewById(R.id.btnsabt);

        btagahi = findViewById(R.id.btagahi);
        btdasteha = findViewById(R.id.btdasteha);
        btchat = findViewById(R.id.btchat);
        btdivareman = findViewById(R.id.btdivareman);

        tvidax = findViewById(R.id.tvidax);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
//                    InputStream imageStream = null;
//                    try {
//                        assert selectedImage != null;
//                        imageStream = getContentResolver().openInputStream(selectedImage);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
//
//                    SaveImage(yourSelectedImage);

                    Picasso.get().load(selectedImage).into(tvidax);
//                    tvidax.setImageBitmap(yourSelectedImage);

                }
        }

    }
//    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
//
//        BitmapFactory.Options o = new BitmapFactory.Options();
//        o.inJustDecodeBounds = true;
//        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);
//
//        final int REQUIRED_SIZE = 140;
//
//        int width_tmp = o.outWidth, height_tmp = o.outHeight;
//        int scale = 1;
//        while (true) {
//            if (width_tmp / 2 < REQUIRED_SIZE
//                    || height_tmp / 2 < REQUIRED_SIZE) {
//                break;
//            }
//            width_tmp /= 2;
//            height_tmp /= 2;
//            scale *= 2;
//        }
//
//        BitmapFactory.Options o2 = new BitmapFactory.Options();
//        o2.inSampleSize = scale;
//        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
//
//    }

//    private void SaveImage(Bitmap yourSelectedImage) {
//
//        String root = Environment.getExternalStorageDirectory().toString();
//        File myDir = new File(root + "/saved_images");
//        myDir.mkdirs();
//        Random generator = new Random();
//        int n = 10000;
//        n = generator.nextInt(n);
//        String fname = "Image-"+ n +".jpg";
//        File file = new File (myDir, fname);
//
//        if (file.exists ()) file.delete ();
//        try {
//            FileOutputStream out = new FileOutputStream(file);
//            yourSelectedImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
//            out.flush();
//            out.close();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    public String BitMapToString(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String temp = Base64.encodeToString(b, Base64.DEFAULT);
//        return temp;
//    }
}