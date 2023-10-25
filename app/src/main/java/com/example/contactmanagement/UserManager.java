package com.example.contactmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserManager {
    SQLiteDatabase db = null;
    Context con;
    UserManager (Context con){
        this.con = con;
    }
    public void open()
    {
        ContactHelper helper = new ContactHelper(con, "Contact.db", null, 2);
        db = helper.getWritableDatabase();
    }
    private boolean userExists(String username) {
        Cursor cursor = db.query(ContactHelper.table_user, new String[]{ContactHelper.col_username},
                ContactHelper.col_username + "=?",
                new String[]{username}, null, null, null);

        boolean userExists = cursor.moveToFirst();
        cursor.close();
        return userExists;
    }
    public long register(String username, String password)
    {
        if (userExists(username)) {
            return -1;
        }
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_username, username);
        values.put(ContactHelper.col_password, password);
        return db.insert(ContactHelper.table_user, null,values );
    }
    public boolean loginUser(String username, String password) {
        Cursor cursor = db.query(ContactHelper.table_user,
                new String[]{ContactHelper.col_username},
                ContactHelper.col_username + "=? AND " + ContactHelper.col_password + "=?",
                new String[]{username, password},
                null, null, null);

        boolean loginSuccessful = cursor.moveToFirst();
        cursor.close();
        return loginSuccessful;
    }

}
