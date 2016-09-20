package com.convector.david_000.convector_valute;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.convector.david_000.convector_valute.data.local.SQLDataUtils;
import com.convector.david_000.convector_valute.data.local.ValuteItem;
import com.convector.david_000.convector_valute.data.remote.XMLPullParserHandler;
import com.convector.david_000.convector_valute.url_connection.HttpConnection;

import java.util.List;

/**
 * Created by gavno on 20.09.16.
 */
public class ValuteLoader extends AsyncTaskLoader<List<ValuteItem>>  {
    private List<ValuteItem> mValutes;
    private Context mContext;

    public ValuteLoader(Context context) {
        super(context);
        mContext=context;

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
        SQLDataUtils dataUtils = new SQLDataUtils(mContext);
        mValutes=dataUtils.getAllValute();
        if(Util.checkInternetConnection(mContext)){
            HttpConnection connection=new HttpConnection(mContext);
            XMLPullParserHandler parser = new XMLPullParserHandler();
            mValutes = parser.parse(connection.getContent());
            dataUtils.putData(mValutes);
        }
        return mValutes;
    }

    @Override
    public void deliverResult(List<ValuteItem> data) {
        mValutes = data; // кешируем в память
        if(mValutes.size()==0)
        {
            Toast.makeText(mContext, mContext.getString(R.string.empty_data), Toast.LENGTH_SHORT)
                    .show();
            return;
        }else {
            Toast.makeText(mContext, "ZBS", Toast.LENGTH_SHORT)
                    .show();
        }

            // тут обрабатываем данные на UI треде

            super.deliverResult(data);
    }

}
