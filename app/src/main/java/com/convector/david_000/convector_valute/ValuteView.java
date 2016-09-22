package com.convector.david_000.convector_valute;

import com.convector.david_000.convector_valute.data.ValuteItem;

import java.util.List;

/**
 * Created by davidbugayov on 21.09.16.
 */
public interface ValuteView {
     void setResult(double result);
     void setExchangeRate(Double buf2);
     void updateValutes(List<ValuteItem> data);
}
