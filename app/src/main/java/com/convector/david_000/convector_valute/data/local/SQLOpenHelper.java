package com.convector.david_000.convector_valute.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by davidbugayov on 18.09.16.
 */
public class SQLOpenHelper extends SQLiteOpenHelper {

    private static final String ADD_ITEM= ""
            + "CREATE TABLE " + DBStayField.TABLE_NAME + "("
            + DBStayField.VALUTE_ID + " INTEGER NOT NULL PRIMARY KEY,"
            + DBStayField.NUM_CODE_VALUTE + " INTEGER,"
            + DBStayField.CHAR_CODE_VALUTE + " TEXT,"
            + DBStayField.NOMINAL_VALUTE + " INTEGER,"
            + DBStayField.NAME_VALUTE + " TEXT,"
            + DBStayField.VALUE_VALUTE + " TEXT"
            + ")";

    public SQLOpenHelper(Context context) {
        super(context, DBStayField.NAME_DB, null, DBStayField.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ADD_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DBStayField.TABLE_NAME);
        onCreate(db);
    }

}
