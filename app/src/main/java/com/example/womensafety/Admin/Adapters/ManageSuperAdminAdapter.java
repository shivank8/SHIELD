package com.example.womensafety.Admin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.womensafety.R;
import com.example.womensafety.SuperAdmin.Models.SuperAdmins;

import java.util.ArrayList;
import java.util.List;

public class ManageSuperAdminAdapter extends ArrayAdapter {
    public ManageSuperAdminAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SuperAdmins> superAdmin) {
        super(context, resource, superAdmin);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.single_manage_admin,parent,false);
        }

        SuperAdmins currentSuperAdmins=(SuperAdmins)getItem(position);

        TextView admin_name=(TextView)listItemView.findViewById(R.id.manage_admin_name);
        TextView admin_mobile=(TextView)listItemView.findViewById(R.id.manage_admin_mobile_number);
        TextView admin_age=(TextView)listItemView.findViewById(R.id.manage_admin_age);
        TextView admin_email=(TextView)listItemView.findViewById(R.id.manage_admin_email_id);
        TextView admin_gender=(TextView)listItemView.findViewById(R.id.manage_admin_gender);
        TextView admin_address=(TextView)listItemView.findViewById(R.id.manage_admin_address);

        String name="Name :- ";
        String age="Age :- ";
        String address="Address :- ";
        String phone="Mobile Number :- ";
        String emailId="Email-Id :- ";
        String gender="Gender :- ";

        name+=currentSuperAdmins.getS_name();
        age+=currentSuperAdmins.getS_age();
        address+=currentSuperAdmins.getS_address();
        phone+=currentSuperAdmins.getS_mob_num();
        emailId+=currentSuperAdmins.getS_email();
        gender+=currentSuperAdmins.getS_gender();

        admin_name.setText(name);
        admin_mobile.setText(phone);
        admin_age.setText(age);
        admin_email.setText(emailId);
        admin_gender.setText(gender);
        admin_address.setText(address);

        return listItemView;
    }
}
