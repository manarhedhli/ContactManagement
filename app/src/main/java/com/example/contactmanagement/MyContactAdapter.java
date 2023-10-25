package com.example.contactmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class MyContactAdapter extends BaseAdapter {
    Context con;
    ArrayList<Contact> data;
    MyContactAdapter (Context con, ArrayList<Contact> data ){
        this.con = con;
        this.data = data;
    }
    @Override
    public int getCount( ){
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(con);
        View v = inf.inflate(R.layout.view_contact, null);
        // Récupérationn des holders
        TextView tv_fname_contact = v.findViewById(R.id.tv_fname_contact);
        TextView tv_lname_contact = v.findViewById(R.id.tv_lname_contact);
        TextView tv_phone_contact = v.findViewById(R.id.tv_phone_contact);


        Contact c = data.get(position);
        tv_fname_contact.setText(c.fname);
        tv_lname_contact.setText(c.lname);
        tv_phone_contact.setText(c.phone);

        return v;


    }


}
