package com.cyberparkitsolutions.jbcc.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cyberparkitsolutions.jbcc.R;


/**
 * Created by vishwajit on 11-06-2018.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    LayoutInflater flater;
    String list[];
    //View view;

    public CustomSpinnerAdapter(Context context, int resouceId, int textviewId, String[] list) {

        super(context, resouceId, textviewId, list);
        //flater = context.getLayoutInflater();
        flater = LayoutInflater.from(context);
        this.list = list;
        //view = View.inflate(context, R.layout.item_spinner, null );
        //Button myButton = (Button) view.findViewById( R.id.myButton );
    }

    public CustomSpinnerAdapter(Activity activity, int resouceId, int textviewId, String[] list) {

        super(activity, resouceId, textviewId, list);
        //flater = context.getLayoutInflater();
        flater = LayoutInflater.from(activity);
        this.list = list;
        //view = View.inflate(context, R.layout.item_spinner, null );
        //Button myButton = (Button) view.findViewById( R.id.myButton );
    }



    @Override
    public boolean isEnabled(int position) {


        if (getItem(position).startsWith("#")) {
            return false;
        } else {
            return true;
        }

    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view = super.getDropDownView(position, convertView, parent);
        TextView txtTitle = (TextView) view;

        //return super.getDropDownView(position, convertView, parent);

        String rowItem = getItem(position);
        //View rowview = flater.inflate(R.layout.item_spinner, null, true);

        //TextView txtTitle = (TextView) rowview.findViewById(R.id.text1);

        Log.e("getView","position - "+position+" data - "+rowItem);
        Log.e("getView","position -"+position+" startwith -"+getItem(position).startsWith("#"));
        Log.e("getView","position -"+position+" listdata substring -"+list[position].substring(0, 1));

        if(!isEnabled(position)){

            //txtTitle.setEnabled(false);
            //txtTitle.setClickable(false);
            txtTitle.setText(rowItem.substring(1, rowItem.length()));
            //txtTitle.setBackgroundColor(Color.parseColor("#fa7763"));
            txtTitle.setBackgroundResource(R.color.greyEnd);

        }else{
            //txtTitle.setEnabled(true);
            //txtTitle.setClickable(true);
            txtTitle.setText(rowItem);
            txtTitle.setBackgroundResource(R.color.transparent);
            //txtTitle.setBackgroundColor(Color.parseColor("#00000000"));

        }



        return view;

    }
}
