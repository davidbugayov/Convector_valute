package com.convector.david_000.convector_valute.data.locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.convector.david_000.convector_valute.SaveDataValute;

/**
 * Created by gavno on 18.09.16.
 */
public class SQLDataUtils extends SQLOpenHelper {

    public SQLDataUtils(Context context) {
        super(context);
    }
    public void putData(){

        SQLiteDatabase db = this.getWritableDatabase();
        super.onUpgrade(db,1,2);
        for(int i=1;i<9;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SaveDataValute.VALUTE_ID, i);
            contentValues.put(SaveDataValute.NUM_CODE_VALUTE, 321);
            contentValues.put(SaveDataValute.CHAR_CODE_VALUTE, "RUS");
            contentValues.put(SaveDataValute.NOMINAL_VALUTE, 100);
            contentValues.put(SaveDataValute.NAME_VALUTE, "Фунт стерлингов Соединенного королевства");
            contentValues.put(SaveDataValute.VALUE_VALUTE, "85,8376");
            db.insert(SaveDataValute.TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        // 1. build the query
        String query = "SELECT  * FROM " + SaveDataValute.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
//        Cursor cursor =
//                db.query(SaveDataValute.TABLE_NAME,
//                        SaveDataValute.COLUMNS,
//                        SaveDataValute.VALUTE_ID+ " = ?",
//                        new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

}
