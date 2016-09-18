package com.convector.david_000.convector_valute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.convector.david_000.convector_valute.url_connection.HttpConnection;

public class MainActivity extends AppCompatActivity {
public TextView testText;
    public String contextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testText=(TextView)(findViewById(R.id.test_text));
        testText.setText(contextView);
        new HttpConnection(this,contextView,testText).execute();
    }

}
