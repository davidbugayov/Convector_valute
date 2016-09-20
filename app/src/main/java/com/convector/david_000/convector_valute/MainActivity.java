package com.convector.david_000.convector_valute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public TextView testText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testText=(TextView)(findViewById(R.id.test_text));
        ValuteLoader loader=new ValuteLoader(this);
        loader.onStartLoading();

    }

}
