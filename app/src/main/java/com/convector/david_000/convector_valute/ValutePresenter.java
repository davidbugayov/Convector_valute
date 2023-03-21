package com.convector.david_000.convector_valute;

import android.app.Activity;

import com.convector.david_000.convector_valute.data.ValuteItem;

import java.util.List;

/**
 * Created by davidbugayov on 22.09.16.
 *
 */

public interface ValutePresenter {
    void setView(Activity valuteViev);
    void ConvertValute(ValuteItem fromNum, ValuteItem toNum, double countValute);

    void deliverResult(List<ValuteItem> mValutes);

    void errorEmptyData();
    void detachView();
}

