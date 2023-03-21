package com.convector.david_000.convector_valute;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.convector.david_000.convector_valute.data.ValuteItem;

import java.util.List;

public class MainActivity extends Activity implements ValuteView, View.OnClickListener{
    private Spinner spinnerValueFrom,spinnerValueTo;
    private EditText course, contentValueTo,contentValueFrom;
    private ArrayAdapter<ValuteItem>adapter;

    ValutePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем экземпляр элемента Spinner
        spinnerValueFrom= (Spinner) findViewById(R.id.spinner_value_from);
        spinnerValueTo = (Spinner) findViewById(R.id.spinner_value_to);
        adapter = new ValueItemsAdapter(MainActivity.this, android.R.layout.simple_spinner_item);
        spinnerValueFrom.setAdapter(adapter);
        spinnerValueTo.setAdapter(adapter);
        contentValueFrom=(EditText)findViewById(R.id.content_value_from);
        course=(EditText)findViewById(R.id.course);
        course.setEnabled(false);
        contentValueTo =(EditText)findViewById(R.id.content_value_to);
        contentValueTo.setEnabled(false);
        Button reset = (Button)findViewById(R.id.reset);
        Button calculate=(Button)findViewById(R.id.calculate);

        presenter = new ValutePresenterImpl();
        presenter.setView(this);

        reset.setOnClickListener(this);
        calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                clickReset();
                break;
            case R.id.calculate:
                clickCalculate();
                break;
        }
    }

    private void clickReset(){
        course.setText("");
        contentValueFrom.setText("");
        contentValueTo.setText("");
    }

    private void clickCalculate(){
        double countValute= 0;

        try {
            countValute  = Double.parseDouble(contentValueFrom.getText().toString().replace(',', '.'));
        }catch (NumberFormatException e){
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }

        ValuteItem valuteItemFrom = adapter.getItem(spinnerValueFrom.getSelectedItemPosition());
        ValuteItem valuteItemTo = adapter.getItem(spinnerValueTo.getSelectedItemPosition());

        if(valuteItemTo != null) {
            presenter.ConvertValute(valuteItemFrom, valuteItemTo, countValute);
        }
    }

    @Override
    public void setResult(double result) {
        contentValueTo.setText(String.valueOf(result));
    }

    @Override
    public void setExchangeRate(Double rate) {
        course.setText(String.valueOf(rate));
    }

    @Override
    public void updateValutes(List<ValuteItem> data) {
        adapter.clear();
        if (data != null){
            for (ValuteItem valuteItem : data) {
                adapter.insert(valuteItem, adapter.getCount());
            }
        }
        adapter.notifyDataSetChanged();
    }
}
