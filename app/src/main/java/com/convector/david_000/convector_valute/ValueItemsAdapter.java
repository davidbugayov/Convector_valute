package com.convector.david_000.convector_valute;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.convector.david_000.convector_valute.data.local.ValuteItem;

import java.util.List;

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
        return  getConvertView(convertView,position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return  getConvertView(convertView,position);
    }

    private View getConvertView(View conView,int position){
        ValuteItem valuteItem = getItem(position);

        if (conView == null) {
            conView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, null);
        }
        ((TextView) conView.findViewById(android.R.id.text1))
                .setText(valuteItem.getCharCode());
        ((TextView) conView.findViewById(android.R.id.text2))
                .setText(valuteItem.getName());
        return conView;
    }
}
