package com.example.divarhesam.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.renderscript.Script;
import android.util.Log;
import android.widget.TableRow;

import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;

import com.example.divarhesam.Const;
import com.example.divarhesam.R;
import com.example.divarhesam.entity.Proudact;

import java.util.ArrayList;

public class ProudactDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = Proudact.class.getSimpleName();
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_VALUE = "value";
    public static final String FIELD_TIME = "time";
    public static final String FILED_IMGID = "imgId";
    public static final String FILED_DETAILS = "details";
    public static final String FILED_NUMBERPHONE = "numberPhone";

    public static final String DATABASE_NAME = "DB.DIVAR";
    public static final int DATABASE_VERSION = 6;


    public ProudactDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e(Const.TAG, "onCreate");

        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createTable(db);

    }

    private void createTable(SQLiteDatabase db){
        String query = "create table IF NOT EXISTS " + TABLE_NAME + " (" +
                FIELD_ID + " Integer primary key , " +
                FIELD_NAME + " varchar(100) , " +
                FIELD_TIME + " varchar(100) , " +
                FIELD_VALUE + " varchar(100) , " +
                FILED_DETAILS + " text , " +
                FILED_NUMBERPHONE + " varchar(50) , " +
                FILED_IMGID + " Integer " +
                ")";

        db.execSQL(query);

    }

    public long insert (Proudact proudact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_NAME , proudact.getName());
        contentValues.put(FIELD_VALUE,proudact.getValue());
        contentValues.put(FIELD_TIME,proudact.getTime());
        contentValues.put(FILED_IMGID, proudact.getImgId());
        contentValues.put(FILED_DETAILS, proudact.getDetails());
        contentValues.put(FILED_NUMBERPHONE, proudact.getNumberPhone());

        return db.insert(TABLE_NAME,null,contentValues);

    }
    public ArrayList<Proudact> select(){
        ArrayList<Proudact> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {FIELD_NAME , FIELD_TIME , FIELD_VALUE , FILED_IMGID , FILED_DETAILS , FILED_NUMBERPHONE};
        Cursor cursor = db.query(TABLE_NAME , columns , null , null , null , null , null);

        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String value = cursor.getString(cursor.getColumnIndex(FIELD_VALUE));
                String time = cursor.getString(cursor.getColumnIndex(FIELD_TIME));
                int imgId = cursor.getInt(cursor.getColumnIndex(FILED_IMGID));
                String details = cursor.getString(cursor.getColumnIndex(FILED_DETAILS));
                String numberPhone = cursor.getString(cursor.getColumnIndex(FILED_NUMBERPHONE));

                Proudact p = new Proudact(name , value,  time , R.drawable.nophoto , details , numberPhone);
                result.add(p);

            }
        }
        result.add(new Proudact("النترا 2015 بی رنگ","توافقی","لحظاتی پیش در جهان آرا", R.drawable.car,"هیوندا النترا 2015 ، گارانتی فعال ، 30 هزار تا کارکرد ، در حد نو ، بدون خط و خش  و رنگ ، مانیتور بزرگ ، دوربین دنده عقب ، آنتن کوسه ای ، سیستم صوتی اینفینیتی ، ایربگ زانو ، صندلی برقی ، مموری صندلی","9108066831"));
        result.add(new Proudact("زعفرانیه ، 157متر ، پارگینگ ، استخر","توافقی","دقایقی پیش در زعفرانیه",R.drawable.khune,"آپارتمان 157 متری به صورت کلاسیک و پاگرد اختصاصی در دنج ترین لوکیشن زعفرانیه با تراس قابل چیدمان و ارتفاع سقف 3.8 متر و پلان تفکیکی ایده آل و غرق در نور ، آشپرخانه فول فرنیش Bosch آلمان ، کابینت ها Enzo ایتالیا ، آسانسور 18 نفره kone فنلاند ، چیلر ها Ebra ژاپن","989123456800"));
        result.add(new Proudact("گربه اسکاتیش دو ماهه","9,000,000 تومان","یک ربع پیش در میرزای شیرازی",R.drawable.cat,"گربه اسکاتیش دو ماهه نر واکسن زده شناسنامه هم داره ، من پت شاپ نیستم ، گربه خونگی کاملا سالم","989196578934"));
        result.add(new Proudact("باربیکیو کباب پز چندکاره","7,950,000 تومان","یک ربع پیش در شهریار",R.drawable.kabab,"دارای بدنه ساخته شده از فولاد درجه یک به وسیله رنگ استایتک به خوبی پوشیده شده است همچنین قسمت هایی از بدنه استنلس استیل می باشد . میتوانیم به عنوان باربیکیو آپارتمانی یا باربکیو باغی استفاده نمود","989127654321"));
        result.add(new Proudact("کفش نیم بوت ساق دار","165,000 تومان","نیم ساعت پیش در شهرک غرب",R.drawable.kafsh,"مستقیم از تولید کننده بدون واسطه ، کیفیت عالی ئرجه یک ، دور دوخت ، سایزبندی بین 40 تا 44 ، ارسال به سراسر کشور ، مدل های نیم بوت 165 هزار تومن و مدل های کوتاه 145 هزار تومان","98121234567"));
        result.add(new Proudact("سامسونگ a7","2,700,000 تومان","1 ساعت پیش در سی متری جی",R.drawable.mobile,"گوشی سالم و بدون ایراده یه قاب و شارژر هم داره تقدیم میشه کارتن هم داره بدون هیچ تعمیری تا الان ، تخفیف هم پای معامله","989122000000"));
        result.add(new Proudact("بانداکتیو acm 500w فلش خور","8,900,000","1 ساعت پیش در امیرآباد",R.drawable.zabt,"با سلام باند ها کاملا سالم و بدون ایراد جفت هستن و تا حالا باز نشدن به شرط کارشناسی یکیش 500 وات فلش خور و دیگری 400 وات هست که جفتشم تمیزه قیمت خوب زدم ","989101231212"));
        result.add(new Proudact("دوچرخه کراس 26 آلومینیوم","2,600,000 تومان","12 ساعت پیش در قیام دشت",R.drawable.docharkhe,"3 الی 4 ماه ازش استفاده کردم و سالم وسالمه ، اگر میخواهید توضیحات بیشتر رو در چت دیوار خواهم داد ","989197865543"));
        result.add(new Proudact("برنج هاشمی","32,000 تومان","5 روز پیش در شهر قدس",R.drawable.berenj,"100 کیلو برنج هاشمی در جه یک امساله ، قیمت هر کیلو 32000 تومان ، قیمت مقطوع","989308067831"));
        result.add(new Proudact("بدنه و سپر سانتافه","توافقی","1 ماه پیش در جمالزاده",R.drawable.dar,"بدنه رنگ فابریک کلیه خودروهای هیوندای کیا موجود ، سپر جلو و عقب و کلیه ی خودروهای هیوندای کیا موجود","989393458787"));

        cursor.close();
        return result;
    }

    public ArrayList<Proudact> selectMyAgahi(){
        ArrayList<Proudact> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {FIELD_NAME , FIELD_TIME , FIELD_VALUE , FILED_IMGID , FILED_DETAILS , FILED_NUMBERPHONE};
        Cursor cursor = db.query(TABLE_NAME , columns , null , null , null , null , null);

        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String value = cursor.getString(cursor.getColumnIndex(FIELD_VALUE));
                String time = cursor.getString(cursor.getColumnIndex(FIELD_TIME));
                int imgId = cursor.getInt(cursor.getColumnIndex(FILED_IMGID));
                String details = cursor.getString(cursor.getColumnIndex(FILED_DETAILS));
                String numberPhone = cursor.getString(cursor.getColumnIndex(FILED_NUMBERPHONE));

                Proudact p = new Proudact(name , value,  time , R.drawable.nophoto , details , numberPhone);
                result.add(p);

            }
        }
        cursor.close();
        return result;
    }

    public int update (Proudact proudact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(FIELD_NAME, proudact.getName());
        contentValues.put(FIELD_VALUE,proudact.getValue());
        contentValues.put(FIELD_TIME,proudact.getTime());
        contentValues.put(FILED_IMGID, proudact.getImgId());
        contentValues.put(FILED_DETAILS, proudact.getDetails());
        contentValues.put(FILED_NUMBERPHONE, proudact.getNumberPhone());

        return db.update(TABLE_NAME , contentValues , "numberPhone=? ", new String[]{String.valueOf(proudact.getNumberPhone())});

    }


    public int delete(Proudact proudact){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "name=?", new String[]{String.valueOf(proudact.getName())});
    }
}
