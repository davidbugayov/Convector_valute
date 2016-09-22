package com.convector.david_000.convector_valute;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.convector.david_000.convector_valute.data.local.ValuteItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ValuteView, View.OnClickListener{
    private Spinner spinnerValueFrom,spinnerValueTo;
    private EditText course, contentValueTo,contentValueFrom;
    private ArrayAdapter<ValuteItem>adapter;
    private List<ValuteItem>valuteItems;

    private LoaderManager.LoaderCallbacks<List<ValuteItem>>
            mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<List<ValuteItem>>() {
                @Override
                public Loader<List<ValuteItem>> onCreateLoader(
                        int id, Bundle args) {
                    ValuteLoader loader=new ValuteLoader(MainActivity.this);
                    loader.valuteView =MainActivity.this;
                    return loader;
                }

                @Override
                public void onLoadFinished(
                        Loader<List<ValuteItem>> loader, List<ValuteItem> data) {
                    valuteItems=data;
                    adapter.clear();
                    if (data != null){
                        for (ValuteItem valuteItem : data) {
                            adapter.insert(valuteItem, adapter.getCount());
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onLoaderReset(Loader<List<ValuteItem>> loader) {
                    adapter.clear();
                }
            };
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
        contentValueTo =(EditText)findViewById(R.id.content_value_to);
        Button reset = (Button)findViewById(R.id.reset);
        Button calculate=(Button)findViewById(R.id.calculate);
        getSupportLoaderManager().initLoader(0, null, mLoaderCallbacks);
        reset.setOnClickListener(this);
        calculate.setOnClickListener(this);
    }

    @Override
    public void deliverResult(final List<ValuteItem> data) {
        valuteItems=data;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                getReset();
                break;
            case R.id.calculate:
                getCalculate();
                break;
        }
    }

    private void  getReset(){
        course.setText("");
        contentValueFrom.setText("");
        contentValueTo.setText("");
    }

    private  void getCalculate(){
        double countValute = Double.parseDouble(contentValueFrom.getText().toString().replace(',', '.'));
        ValuteItem valuteItemFrom=valuteItems.get(spinnerValueFrom.getSelectedItemPosition());
        ValuteItem valuteItemTo=valuteItems.get(spinnerValueTo.getSelectedItemPosition());
        if(countValute ==0){
            Toast.makeText(this, R.string.Error_empty_field, Toast.LENGTH_SHORT).show();
        }else  if(valuteItemTo!=null|valuteItemTo!=null) {
            Double buf1=(StringToDouble(valuteItemFrom.getValue())
                    /Double.parseDouble(valuteItemFrom.getNominal()))* countValute;
            Double buf2=buf1/StringToDouble(valuteItemTo.getValue())*
                    Double.parseDouble(valuteItemTo.getNominal());
            contentValueTo.setText(String.valueOf(buf2));
            buf1=(StringToDouble(valuteItemFrom.getValue())
                    /Double.parseDouble(valuteItemFrom.getNominal()));
            buf2=buf1/StringToDouble(valuteItemTo.getValue())*
                    Double.parseDouble(valuteItemTo.getNominal());
            course.setText(String.valueOf(buf2));
        }
    }
    private double StringToDouble(String data) {
        return  Double.parseDouble(data.replace(',', '.'));
    }

}
