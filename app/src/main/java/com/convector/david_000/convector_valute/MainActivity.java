package com.convector.david_000.convector_valute;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.convector.david_000.convector_valute.data.locale.SQLOpenHelper;
import com.convector.david_000.convector_valute.url_connection.HttpConnection;

public class MainActivity extends AppCompatActivity {
public TextView testText;
    public String contextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testText=(TextView)(findViewById(R.id.test_text));

        SQLOpenHelper sqlOpenHelper= new SQLOpenHelper(this);
        Cursor cursor= sqlOpenHelper.getData(1);
        String nam = cursor.getString(cursor.getColumnIndex(SaveDataValute.NUM_CODE_VALUTE));
        String phon = cursor.getString(cursor.getColumnIndex(SaveDataValute.CHAR_CODE_VALUTE));
        String emai = cursor.getString(cursor.getColumnIndex(SaveDataValute.NOMINAL_VALUTE));
        String stree = cursor.getString(cursor.getColumnIndex(SaveDataValute.NAME_VALUTE));
        String plac = cursor.getString(cursor.getColumnIndex(SaveDataValute.VALUE_VALUTE));
        testText.setText(nam +" "+ phon+ emai+stree+plac);
       // new HttpConnection(this,contextView,testText).execute();
    }

}
