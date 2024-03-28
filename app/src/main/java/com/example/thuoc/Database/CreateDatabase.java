package com.example.thuoc.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {

    public CreateDatabase(@Nullable Context context) {
        super(context, "OrderMedicine", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TB_NGUOIDUNG(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,address TEXT,phone TEXT, pass TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists TB_NGUOIDUNG");
        onCreate(db);
    }

    public SQLiteDatabase open() {

        return this.getWritableDatabase();
    }

    public boolean insertData(String name, String address, String phone, String pass) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("address",address);
        cv.put("phone",phone);
        cv.put("pass",pass);

        long result = db.insert("TB_NGUOIDUNG",null,cv);
        if(result == -1){
            return false;
        }else {
            return true;
        }


    }
    public  boolean checkPhonePass (String phone, String pass){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from TB_NGUOIDUNG where phone = ? and pass=?",new String[]{phone,pass});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }

    }
    public  boolean checkPhone (String phone){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from TB_NGUOIDUNG where phone = ? ",new String[]{phone});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }

    }


}