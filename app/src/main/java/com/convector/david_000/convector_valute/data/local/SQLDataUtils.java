package com.convector.david_000.convector_valute.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.convector.david_000.convector_valute.SaveDataValute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavno on 18.09.16.
 */
public class SQLDataUtils extends SQLOpenHelper {

    public SQLDataUtils(Context context) {
        super(context);
    }

    public void putData(List<ValuteItem> data){

        SQLiteDatabase db = this.getWritableDatabase();
        super.onUpgrade(db,1,2);

        for(int i=0;i<data.size();i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SaveDataValute.VALUTE_ID, data.get(i).getValuteID());
            contentValues.put(SaveDataValute.NUM_CODE_VALUTE, data.get(i).getNumCode());
            contentValues.put(SaveDataValute.CHAR_CODE_VALUTE, data.get(i).getCharCode());
            contentValues.put(SaveDataValute.NOMINAL_VALUTE, data.get(i).getNominal());
            contentValues.put(SaveDataValute.NAME_VALUTE, data.get(i).getName());
            contentValues.put(SaveDataValute.VALUE_VALUTE, data.get(i).getValue());
            db.insert(SaveDataValute.TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(SaveDataValute.TABLE_NAME,
                        SaveDataValute.COLUMNS,
                        SaveDataValute.VALUTE_ID+ " = ?",
                        new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        db.close();
        return cursor;
    }

    public List<ValuteItem> getAllValute() {
        List<ValuteItem> valuteItems = new ArrayList<>();
        String query = "SELECT  * FROM " + SaveDataValute.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ValuteItem valuteItem = null;
        if (cursor.moveToFirst()) {
            do {
                valuteItem = new ValuteItem();
                valuteItem.setValuteID(Integer.parseInt(cursor.getString(0)));
                valuteItem.setNumCode(cursor.getString(1));
                valuteItem.setCharCode(cursor.getString(2));
                valuteItem.setNominal(cursor.getString(3));
                valuteItem.setName(cursor.getString(4));
                valuteItem.setValue(cursor.getString(5));
                valuteItems.add(valuteItem);
            } while (cursor.moveToNext());
        }
        // return books
        return valuteItems;
    }

}
