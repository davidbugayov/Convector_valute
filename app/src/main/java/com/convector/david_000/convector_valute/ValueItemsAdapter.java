package com.convector.david_000.convector_valute;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.convector.david_000.convector_valute.data.local.ValuteItem;

/**
 * Created by davidbugayov on 21.09.16.
 */
public class ValueItemsAdapter  extends ArrayAdapter<ValuteItem> {
    public ValueItemsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {

        ValuteItem valuteItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView textView1 =((TextView) convertView.findViewById(android.R.id.text1));
        textView1.setTextColor(Color.argb(255,48, 63, 159));
        textView1.setText(valuteItem.getCharCode());
        TextView textView2 =((TextView) convertView.findViewById(android.R.id.text2));
        textView2.setTextColor(Color.argb(255,48, 63, 159));
        textView2 .setText(valuteItem.getName());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ValuteItem valuteItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView textView1 =((TextView) convertView.findViewById(android.R.id.text1));
        textView1.setTextColor(Color.WHITE);
        textView1.setText(valuteItem.getCharCode());
        TextView textView2 =((TextView) convertView.findViewById(android.R.id.text2));
        textView2.setTextColor(Color.WHITE);
        textView2 .setText(valuteItem.getName());
        return convertView;
    }

}
