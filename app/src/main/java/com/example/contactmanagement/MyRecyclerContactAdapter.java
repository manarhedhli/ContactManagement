package com.example.contactmanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.Manifest;

public class MyRecyclerContactAdapter extends RecyclerView.Adapter<MyRecyclerContactAdapter.MyViewHolder> {

    Context con;
    ArrayList<Contact> data;


    public MyRecyclerContactAdapter(Context con, ArrayList<Contact> data) {
        this.con = con;
        this.data = data;
    }

    public void updateData(ArrayList<Contact> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRecyclerContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(con);
        View v = inf.inflate(R.layout.view_contact, null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerContactAdapter.MyViewHolder holder, int position) {
        // Récupérationn des holders
        Contact c = data.get(position);
        holder.tv_fname_contact.setText(c.fname);
        holder.tv_lname_contact.setText(c.lname);
        holder.tv_phone_contact.setText(c.phone);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fname_contact, tv_lname_contact, tv_phone_contact;
        ImageView btn_delete, btn_Edit, btn_Call;

        public MyViewHolder(@NonNull View v) {
            super(v);
            tv_fname_contact = v.findViewById(R.id.tv_fname_contact);
            tv_lname_contact = v.findViewById(R.id.tv_lname_contact);
            tv_phone_contact = v.findViewById(R.id.tv_phone_contact);
            btn_delete = v.findViewById(R.id.btn_delete_contact);
            btn_Edit = v.findViewById(R.id.btn_edit_contact);
            btn_Call =  v.findViewById(R.id.btn_call_contact);


            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);
                    alertDialogBuilder.setMessage("Are you sure you want to delete this contact?");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ContactManager manager = new ContactManager(v.getContext());
                            manager.open();
                            long r =0;
                            r = manager.delete(data.get(position).phone);
                            manager.close();
                            data.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });

            btn_Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerte = new AlertDialog.Builder(con);
                    alerte.setTitle("Edit");
                    LayoutInflater inf = LayoutInflater.from(con);
                    View dialog = inf.inflate(R.layout.view_dialog, null);

                    EditText ed_fname_dialog = dialog.findViewById(R.id.ed_fname_dialog);
                    EditText ed_lname_dialog = dialog.findViewById(R.id.ed_lname_dialog);
                    TextView tv_phone_dialog = dialog.findViewById(R.id.tv_phone_dialog);

                    Button btn_cancel_dialog = dialog.findViewById(R.id.btn_cancel_dialog);
                    Button btn_update_dialog = dialog.findViewById(R.id.btn_update_dialog);

                    int position = getAdapterPosition();
                    Contact c = data.get(position);

                    ed_fname_dialog.setText(c.fname);
                    ed_lname_dialog.setText(c.lname);
                    tv_phone_dialog.setText(c.phone);

                    alerte.setView(dialog);

                    AlertDialog alerteDialog = alerte.create();
                    alerteDialog.show();

                    btn_cancel_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alerteDialog.dismiss();
                        }
                    });

                    btn_update_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String newFname = ed_fname_dialog.getText().toString();
                            String newLname = ed_lname_dialog.getText().toString();
                            String phone = tv_phone_dialog.getText().toString();
                            ContactManager manager = new ContactManager(v.getContext());
                            manager.open();
                            manager.update(phone,  newFname, newLname);
                            c.fname = newFname;
                            c.lname = newLname;
                            c.phone = phone;
                            data.set(position, c);
                            notifyDataSetChanged();
                            alerteDialog.dismiss();

                        }
                    });

                }
            });

            btn_Call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = tv_phone_contact.getText().toString();
                    if (ContextCompat.checkSelfPermission(con, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + number));
                        con.startActivity(intent);
                    } else {
                        ActivityCompat.requestPermissions((Display)con, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        System.out.println("No permission");
                    }
                }

            });




        }


    }

}
