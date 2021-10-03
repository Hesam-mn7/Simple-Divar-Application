package com.example.divarhesam.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.divarhesam.entity.User;

import java.util.ArrayList;

public class UserDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB.USER";
    public static final int DATABASE_VERSION = 6;

    public static final String TABLE_NAME_USER = "User";

    public static final String FILED_NUMBER = "number";

    public UserDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createTable(db);

    }

    public void createTable(SQLiteDatabase db){
        String query = "create table IF NOT EXISTS " + TABLE_NAME_USER + " (" +
             FILED_NUMBER + " varchar(20) " +
             ")";
        db.execSQL(query);
    }
    public long insert(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FILED_NUMBER , user.getNumber());

        return db.insert(TABLE_NAME_USER,null,contentValues);

    }
    public ArrayList<User> select(){

        ArrayList<User> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {FILED_NUMBER};
        Cursor cursor = db.query(TABLE_NAME_USER , columns , null , null , null , null , null );

        if (cursor.getCount()>0){
            while(cursor.moveToNext()){
                String number = cursor.getString(cursor.getColumnIndex(FILED_NUMBER));

                User u = new User(number);
                result.add(u);
            }
        }
        cursor.close();
        return result;
    }
    public void deleteUser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME_USER);
        db.close();

    }

    public boolean checkEmptyDataBase(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER, null);
        Boolean rowExists ;

        if (mCursor.moveToFirst())
        {
            // DO SOMETHING WITH CURSOR
            rowExists = true;

        } else
        {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }



}
