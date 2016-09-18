package com.convector.david_000.convector_valute.data.locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.convector.david_000.convector_valute.SaveDataValute;

/**
 * Created by gavno on 18.09.16.
 */
public class SQLOpenHelper extends SQLiteOpenHelper {
    private static final String ADD_ITEM= ""
            + "CREATE TABLE " + SaveDataValute.TABLE_NAME + "("
            + SaveDataValute.VALUTE_ID + " INTEGER NOT NULL PRIMARY KEY,"
            + SaveDataValute.NUM_CODE_VALUTE + " INTEGER,"
            + SaveDataValute.CHAR_CODE_VALUTE + " TEXT,"
            + SaveDataValute.NOMINAL_VALUTE + " INTEGER,"
            + SaveDataValute.NAME_VALUTE + " TEXT,"
            + SaveDataValute.VALUE_VALUTE + " TEXT"
            + ")";

    public SQLOpenHelper(Context context) {
        super(context, SaveDataValute.NAME_DB, null, SaveDataValute.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ADD_ITEM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+SaveDataValute.TABLE_NAME+"");
        onCreate(db);
    }
    public Cursor getData(int id){
//        SQLiteDatabase db1 = this.getReadableDatabase();
//        for(int i=1;i<10;i++) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SaveDataValute.VALUTE_ID, i);
//            contentValues.put(SaveDataValute.NUM_CODE_VALUTE, 321);
//            contentValues.put(SaveDataValute.CHAR_CODE_VALUTE, "RUS");
//            contentValues.put(SaveDataValute.NOMINAL_VALUTE, 100);
//            contentValues.put(SaveDataValute.NAME_VALUTE, "Фунт стерлингов Соединенного королевства");
//            contentValues.put(SaveDataValute.VALUE_VALUTE, "85,8376");
//            db1.insert(SaveDataValute.TABLE_NAME, null, contentValues);
//        }
//        db1.close();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery( "select * from Valute", null );
        // Cursor cursor= db.query(true, SaveDataValute.TABLE_NAME,
        //         new String[]{SaveDataValute.VALUTE_ID}, "1", null, null, null, null, null);
        return cursor;
    }
}
