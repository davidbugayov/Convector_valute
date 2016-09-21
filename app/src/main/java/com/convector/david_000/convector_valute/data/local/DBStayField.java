package com.convector.david_000.convector_valute.data.local;

/**
 * Created by davidbugayov on 18.09.16.
 */
public class DBStayField {
    public static  int VERSION_DB = 1;
    public static final String NAME_DB = "valute.db";
    //table field
    public static final String TABLE_NAME = "Valute";
    public static final String VALUTE_ID = "Valute_ID";
    public static final String NUM_CODE_VALUTE = "NumCode";
    public static final String CHAR_CODE_VALUTE = "CharCode";
    public static final String NOMINAL_VALUTE = "Nominal";
    public static final String NAME_VALUTE = "Name";
    public static final String VALUE_VALUTE = "Value";
    public static final String[] COLUMNS = {VALUTE_ID,NUM_CODE_VALUTE,CHAR_CODE_VALUTE,
            NOMINAL_VALUTE,NAME_VALUTE,VALUE_VALUTE};
}
