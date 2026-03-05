package com.example.codefesttry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperMe extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UsersDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "Users";
    public  DatabaseHelperMe(Context context){
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "(username VARCHAR, email VARCHAR UNIQUE, pass VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    // CREATE
    public boolean UserEntries(String username, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("pass", pass);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result !=-1;
    }
    // READ
    public boolean checkUser (String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE email = ? AND  pass = ?", new String[]{email, password});
        boolean exists = cursor.getCount()>0;
        cursor.close();
        db.close();
        return exists;
    }
    // UPDATE
    public boolean updateUsers(String email, String newPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pass", newPassword);

        int rows = db.update(TABLE_USERS, values, "email = ?", new String[]{email});
        db.close();
        return rows>0;
    }
    // DELETE
    public boolean deleteUsers(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_USERS, "email = ?", new String[]{email});
        db.close();
        return rows>0;
    }
}
