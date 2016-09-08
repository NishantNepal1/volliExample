package com.hfad.voliexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hfad.voliexample.R;
import com.hfad.voliexample.modal.Contact;

import java.util.ArrayList;

/**
 * Created by user on 9/7/2016.
 */
public class InfoAdapter extends ArrayAdapter {
    ArrayList<Contact> info;
    Context context;
    public InfoAdapter(Context context, int resource,ArrayList<Contact> infos) {
        super(context, resource,infos);
        this.info=infos;
        this.context=context;
    }

    @Override
    public int getCount() {return info.size();}
    @Override
    public Contact getItem(int position) {return info.get(position);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.listdata,null);
        TextView name=(TextView)convertView.findViewById(R.id.dispName);
        TextView address=(TextView) convertView.findViewById(R.id.dispAdress);
        TextView mobile=(TextView) convertView.findViewById(R.id.dispMobile);
        TextView gender=(TextView) convertView.findViewById(R.id.dispGender);
        TextView email=(TextView) convertView.findViewById(R.id.dispEmail);
        TextView id=(TextView) convertView.findViewById(R.id.dispId);
        TextView home=(TextView) convertView.findViewById(R.id.dispHome);;
        TextView office=(TextView) convertView.findViewById(R.id.dispOffice);

        Contact info=getItem(position);
        name.setText(info.getName());
        address.setText(info.getAddress());
        mobile.setText(info.getPhone().getMobile());
        gender.setText(info.getGender());
        id.setText(info.getId());
        email.setText(info.getEmail());
        home.setText(info.getPhone().getHome());
        office.setText(info.getPhone().getOffice());

        return convertView;
    }
    public void notifyData(ArrayList<Contact> list) {
        this.info = list;
        notifyDataSetChanged();
    }
}
