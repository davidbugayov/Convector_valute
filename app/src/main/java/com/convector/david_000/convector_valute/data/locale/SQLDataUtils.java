package com.convector.david_000.convector_valute.data.locale;

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

}
