package com.example.admin_demo;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

import android.content.ContentValues;

import android.database.Cursor;

public class My_DB extends SQLiteOpenHelper {

    //information of database

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "App.db";

    public static final String TABLE_NAME = "User";

    public static final String COLUMN_Username = "Username";

    public static final String COLUMN_password = "Password";

    public static final String CaOLUMN_EMAIL="user_id";

    

    //initialize the database

    public My_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_Username +

                " VARCHAR(20) PRIMARY KEY," + COLUMN_password + " VARCHAR(8) )";

        db.execSQL(CREATE_TABLE);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public String loadHandler() {


        String result = "";

        String query = "Select * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {

            String result_0 = cursor.getString(0);

            // String result_1 = cursor.getString(1);

            result += result_0 + " " +

                    System.getProperty("line.separator");

        }

        cursor.close();

        db.close();

        return result;

    }

    public void addHandler(User user) {


        ContentValues values = new ContentValues();

        values.put(COLUMN_Username, user.getUsername());

        values.put(COLUMN_password, user.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NAME, null, values);

        db.close();

    }


    public User findHandler(String username) {

        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_Username + " = " + "'" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            user.setUsername(cursor.getString(0));

            user.setPassword(cursor.getString(1));

            cursor.close();

        } else {

            user = null;

        }

        db.close();

        return user;
    }

    public boolean deleteHandler(String username) {

        boolean result = false;

        String query = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_Username + "= '" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {

            user.setUsername(cursor.getString(0));

            db.delete(TABLE_NAME, COLUMN_Username + "=?",

                    new String[] {

                            String.valueOf(user.getUsername())

                    });

            cursor.close();

            result = true;

        }

        db.close();

        return result;
    }

    public boolean updateHandler(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(COLUMN_Username, username);

        args.put(COLUMN_password, password);

        return db.update(TABLE_NAME, args, COLUMN_Username + "=" + username, null) > 0;
    }

}

