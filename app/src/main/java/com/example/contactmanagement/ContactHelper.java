package com.example.contactmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {

    public static final String table_contact = "Contact";
    public static final String col_phone = "Phone";
    public static final String col_fname = "Fname";
    public static final String col_lname = "Lname";

    public static final String table_user = "User";
    public static final String col_username = "Username";
    public static final String col_password = "password";
    public static final String col_user_id = "User";



    public ContactHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String requeteUser = "CREATE TABLE " + table_user +
                " (" + col_username + " TEXT PRIMARY KEY, " +
                col_password + " TEXT NOT NULL);";
        String requeteContact = "CREATE TABLE " + table_contact + " (" +
                col_phone + " TEXT PRIMARY KEY, " +
                col_fname + " TEXT NOT NULL, " +
                col_lname + " TEXT NOT NULL, " +
                col_user_id + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + col_user_id + ") REFERENCES " + table_user + "(" + col_username + "));";


        db.execSQL(requeteContact);
        db.execSQL(requeteUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+table_contact);
        db.execSQL("drop table "+table_user);
        onCreate(db);
    }
}
