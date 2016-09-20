package com.convector.david_000.convector_valute;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.convector.david_000.convector_valute.data.locale.SQLDataSource;
import com.convector.david_000.convector_valute.data.locale.SQLDataUtils;
import com.convector.david_000.convector_valute.data.locale.SQLOpenHelper;
import com.convector.david_000.convector_valute.url_connection.HttpConnection;

public class MainActivity extends AppCompatActivity {
public TextView testText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testText=(TextView)(findViewById(R.id.test_text));
//
//        SQLOpenHelper sqlOpenHelper= new SQLOpenHelper(this);
//        SQLDataUtils sqlDataSource= new SQLDataUtils(this);
//        sqlDataSource.putData();
//        Cursor cursor= sqlDataSource.getData(1);
//        do{
//        String nam = cursor.getString(cursor.getColumnIndex(SaveDataValute.NUM_CODE_VALUTE));
//        String phon = cursor.getString(cursor.getColumnIndex(SaveDataValute.CHAR_CODE_VALUTE));
//        String emai = cursor.getString(cursor.getColumnIndex(SaveDataValute.NOMINAL_VALUTE));
//        String stree = cursor.getString(cursor.getColumnIndex(SaveDataValute.NAME_VALUTE));
//        String plac = cursor.getString(cursor.getColumnIndex(SaveDataValute.VALUE_VALUTE));
//        testText.append(nam +" "+ phon+ emai+stree+plac+"\n");}
//        while (cursor.moveToNext());
//
//
      // testText.setText(cursor.getString(1));
    }

}
