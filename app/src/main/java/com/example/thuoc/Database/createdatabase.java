package com.example.thuoc.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class createdatabase extends SQLiteOpenHelper {
    public static String  TBTHUOC = "THUOC";

    public static String THUOC_TENTHUOC = "TENSP";
    public static String THUOC_PRICE = "GIASP";

    public static String MOTA = "MOTA";




//, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version
    public createdatabase(@Nullable Context context) {
        super(context, "Chi tiết sản phẩm", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbtensanpham ="CREATE TABLE "+TBTHUOC+" ( "+THUOC_TENTHUOC +"TEXT" +THUOC_PRICE+"INTEGER";
        db.execSQL(TBTHUOC);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
