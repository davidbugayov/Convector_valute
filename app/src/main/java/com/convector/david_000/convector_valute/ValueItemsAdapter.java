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
 * Created by gavno on 21.09.16.
 */
public class ValueItemsAdapter  extends ArrayAdapter<ValuteItem> {
    public ValueItemsAdapter(Context context, int resource, List<ValuteItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        ValuteItem valuteItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_spinner_dropdown_item, null);
        }
        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(valuteItem.getName());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ValuteItem valuteItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_spinner_item, null);
        }
        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(valuteItem.getName());
        return convertView;
    }
}
