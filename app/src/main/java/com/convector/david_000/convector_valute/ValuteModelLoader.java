package com.convector.david_000.convector_valute;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.convector.david_000.convector_valute.data.local.SQLDataUtils;
import com.convector.david_000.convector_valute.data.local.Util;
import com.convector.david_000.convector_valute.data.ValuteItem;
import com.convector.david_000.convector_valute.data.remote.XMLPullParserHandler;
import com.convector.david_000.convector_valute.data.remote.HttpConnection;

import java.util.List;

/**
 * Created by davidbugayov on 20.09.16.
 */
public class ValuteModelLoader extends AsyncTaskLoader<List<ValuteItem>> {
    public ValutePresenter valutePresenter;
    private List<ValuteItem> mValutes;
    private Context mContext;

    public ValuteModelLoader(Context context) {
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

        if(mValutes != null) {
            if (mValutes.size() == 0) {
                valutePresenter.errorEmptyData();
                return;
            } else {
                valutePresenter.deliverResult(mValutes);
            }
        }

        super.deliverResult(mValutes);
    }
}
