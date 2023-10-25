package com.example.contactmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db = null;
    Context con;
    ContactManager (Context con){
        this.con = con;
    }
    public void open()
    {
        ContactHelper helper = new ContactHelper(con, "Contact.db", null, 2);
        db = helper.getWritableDatabase();
    }
    public long add(String phone, String fname, String lname, String userId )
    {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_phone, phone);
        values.put(ContactHelper.col_fname, fname);
        values.put(ContactHelper.col_lname, lname);
        values.put(ContactHelper.col_user_id, userId);
        return db.insert(ContactHelper.table_contact, null,values );

    }

    public ArrayList<Contact> getAllContact(String userId) {
        ArrayList<Contact> l = new ArrayList<Contact>();
        Cursor cr = db.query(ContactHelper.table_contact,
                new String[]{ContactHelper.col_phone,
                        ContactHelper.col_fname,
                        ContactHelper.col_lname,},
                ContactHelper.col_user_id + " = ?",
                new String[]{userId},
                null, null, null);
        cr.moveToFirst();

       while (! cr.isAfterLast()){
            String phone = cr.getString(0);
            String fname = cr.getString(1);
            String lname = cr.getString(2);
            l.add(new Contact(fname, lname, phone));
            cr.moveToNext();
        }
        return l;
    }
    public int update(String phone, String newFname, String newLname) {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_fname, newFname);
        values.put(ContactHelper.col_lname, newLname);
        int r = db.update(ContactHelper.table_contact, values, ContactHelper.col_phone+ "=?", new String[]{phone});
        return r;
    }

    public int delete(String phone) {
        int r = db.delete(ContactHelper.table_contact, ContactHelper.col_phone + "=?", new String[]{phone}  );
        return r;
    }
    public void close()
    {

    }
}
