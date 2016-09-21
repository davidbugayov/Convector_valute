package com.convector.david_000.convector_valute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.convector.david_000.convector_valute.data.local.ValuteItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ValuteView{
    private ValuteLoader loader;
    public Spinner spinnerValueFrom,spinnerValueTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ValuteLoader loader=new ValuteLoader(this);
        loader.delegate=this;
        loader.onStartLoading();
        // Получаем экземпляр элемента Spinner
        spinnerValueFrom= (Spinner) findViewById(R.id.spinner_value_from);
        spinnerValueTo = (Spinner) findViewById(R.id.spinner_value_to);
    }

    @Override
    public void deliverResult(List<ValuteItem> data) {
        // Настраиваем адаптер
        ArrayAdapter<ValuteItem> adapter =
                new ValueItemsAdapter(this, android.R.layout.simple_spinner_item, data);
        spinnerValueFrom.setAdapter(adapter);
        spinnerValueTo.setAdapter(adapter);
        spinnerValueTo.setSelection(2);
    }
}
