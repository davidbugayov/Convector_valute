package com.convector.david_000.convector_valute;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.convector.david_000.convector_valute.data.locale.SQLDataUtils;
import com.convector.david_000.convector_valute.data.locale.ValuteItem;
import com.convector.david_000.convector_valute.data.remote.XMLPullParserHandler;
import com.convector.david_000.convector_valute.url_connection.HttpConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavno on 20.09.16.
 */
public class ValuteLoader extends AsyncTaskLoader<List<ValuteItem>>  {
    private List<ValuteItem> mValutes;
    private Context mContext;
    private SQLDataUtils dataUtils;
    public ValuteLoader(Context context) {
        super(context);
        mContext=context;
        dataUtils=new SQLDataUtils(mContext);
    }

    @Override
    protected void onStartLoading() {
        if (mValutes != null) {
            deliverResult(mValutes); // из кеша
        } else {
            forceLoad(); // нет кеша в памяти - грузим данные
        }
    }

    @Override
    public List<ValuteItem> loadInBackground() {
        //получаем что есть , вдруг у нас интеренет соедение плохое , пока грузиться
        mValutes=dataUtils.getAllValute();
        if(Util.checkInternetConnection(mContext)){
            new HttpConnection(mContext,new AsyncResponse(){
                @Override
                public void processFinish(String output){
                    XMLPullParserHandler parser = new XMLPullParserHandler();
                    mValutes = parser.parse(output);
                    dataUtils.putData(mValutes);
                }
            }).execute();
        }
        return mValutes;
    }

    @Override
    public void deliverResult(List<ValuteItem> data) {
        mValutes = data; // кешируем в память

        // тут обрабатываем данные на UI треде

        super.deliverResult(data);
    }

}
