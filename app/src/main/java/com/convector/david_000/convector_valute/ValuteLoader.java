package com.convector.david_000.convector_valute;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.convector.david_000.convector_valute.data.local.SQLDataUtils;
import com.convector.david_000.convector_valute.data.local.Util;
import com.convector.david_000.convector_valute.data.local.ValuteItem;
import com.convector.david_000.convector_valute.data.remote.XMLPullParserHandler;
import com.convector.david_000.convector_valute.data.remote.HttpConnection;

import java.util.List;

/**
 * Created by davidbugayov on 20.09.16.
 */
public class ValuteLoader extends AsyncTaskLoader<List<ValuteItem>>  {
    public ValuteView valuteView;
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

        final SQLDataUtils dataUtils = new SQLDataUtils(mContext);
       mValutes = dataUtils.getAllValute();
        if(Util.checkInternetConnection(mContext)){
            new HttpConnection(mContext,new AsyncResponse(){
                @Override
                public void processFinish(final String output){
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            XMLPullParserHandler parser = new XMLPullParserHandler(output);
                            mValutes = parser.getValuteItems();
                            dataUtils.putData(mValutes);
                            deliverResult(mValutes);
                        }
                    });
                }
            }).execute();
        }

        return mValutes;
    }

    @Override
    public void deliverResult(List<ValuteItem> data) {
        mValutes = data; // кешируем в память
        if(mValutes!=null)
        if(mValutes.size()==0)
        {
            Toast.makeText(mContext, mContext.getString(R.string.empty_data), Toast.LENGTH_SHORT)
                    .show();
            return;
        }else {
                valuteView.deliverResult(mValutes);
        }

            super.deliverResult(mValutes);
    }

}
