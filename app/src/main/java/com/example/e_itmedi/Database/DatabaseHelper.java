package com.example.e_itmedi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Student_DB";
    private static final String TABLE_NAME = "products";
    private static final String TITLE = "Title";
    private static final String PRICE = "Price";
    private static final String DETAILS = "Details";
    private static final String ID = "id_";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final int VERSION_NUM = 2;
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " VARCHAR(20)  PRIMARY KEY ," + TITLE + " VARCHAR(50)," + DETAILS + " VARCHAR(50)," + PRICE + " INTEGER )";
    Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "oncreate", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "EXCEPTION" + e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String title, String id, String price, String details) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, title);
        contentValues.put(ID, id);
        contentValues.put(PRICE, price);
        contentValues.put(DETAILS, details);
        long row = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return row;
    }

    public Cursor dsiplayData() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return cursor;
    }
}
