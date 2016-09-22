package com.convector.david_000.convector_valute;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.convector.david_000.convector_valute.data.local.SQLDataUtils;
import com.convector.david_000.convector_valute.data.local.ValuteItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ValuteView, TextWatcher,View.OnClickListener{
    private Spinner spinnerValueFrom,spinnerValueTo;
    private double countValute =0;
    private ValuteItem valuteItemFrom,valuteItemTo;
    private EditText course, contentValueTo,contentValueFrom;
    private ArrayAdapter<ValuteItem>adapter;

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
        contentValueFrom.addTextChangedListener(this);
    }

    @Override
    public void deliverResult(final List<ValuteItem> data) {
        adapter.clear();
        if (data != null){
            for (ValuteItem valuteItem : data) {
                adapter.insert(valuteItem, adapter.getCount());
            }
        }
        adapter.notifyDataSetChanged();
        spinnerValueFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valuteItemFrom=data.get(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        } );
        spinnerValueTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valuteItemTo=data.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        } );
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
        countValute =0;
    }

    private  void getCalculate(){
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

    //------------------------------------------------------------------------------
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    @Override
    public void afterTextChanged(Editable s) {
        if (s != null) {
            try {
                countValute = Double.parseDouble(s.toString().replace(',', '.'));
            } catch (NumberFormatException e) {
                //Error
                if(s.equals("")){
                    Toast.makeText(this, R.string.Error_uncorrect_symbol, Toast.LENGTH_SHORT)
                            .show();
                    e.printStackTrace();}
            }
        }
        //Do something with doubleValue
    }


}
