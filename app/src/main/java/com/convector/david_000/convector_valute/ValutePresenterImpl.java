package com.convector.david_000.convector_valute;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.convector.david_000.convector_valute.data.ValuteItem;

import java.util.List;

/**
 * Created by davidbugayov on 22.09.16.
 */

public class ValutePresenterImpl implements ValutePresenter {
    private ValuteView valuteView;

    private LoaderManager.LoaderCallbacks<List<ValuteItem>>
            mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<List<ValuteItem>>() {
                @Override
                public Loader<List<ValuteItem>> onCreateLoader(int id, Bundle args) {
                    ValuteModelLoader loader = new ValuteModelLoader((AppCompatActivity)valuteView);
                    loader.valutePresenter = ValutePresenterImpl.this;
                    return loader;
                }

                @Override
                public void onLoadFinished(Loader<List<ValuteItem>> loader, List<ValuteItem> data) {
                    valuteView.updateValutes(data);
                }

                @Override
                public void onLoaderReset(Loader<List<ValuteItem>> loader) {
                    valuteView.updateValutes(null);
                }
            };

    @Override
    public void setView(AppCompatActivity valuteViev) {
        this.valuteView = (ValuteView) valuteViev;

        valuteViev.getSupportLoaderManager().initLoader(0, null, mLoaderCallbacks);
    }

    @Override
    public void ConvertValute(ValuteItem fromNum, ValuteItem toNum, double amount) {
        Double buf1 = (fromNum.getValue() /fromNum.getNominal()) * amount;
        Double buf2 = buf1 / toNum.getValue() * toNum.getNominal();

        valuteView.setResult(buf2);
        buf1 = fromNum.getValue() / fromNum.getNominal();
        buf2 = buf1 / toNum.getValue() * toNum.getNominal();

        valuteView.setExchangeRate(buf2);
    }

    @Override
    public void deliverResult(List<ValuteItem> mValutes) {
        valuteView.updateValutes(mValutes);
    }

    @Override
    public void errorEmptyData() {
        Toast.makeText((AppCompatActivity)valuteView, ((AppCompatActivity)valuteView).getString(R.string.empty_data), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void detachView() {
            this.valuteView=null;
    }

}
