package com.convector.david_000.convector_valute;

import android.support.test.runner.AndroidJUnit4;

import com.convector.david_000.convector_valute.data.ValuteItem;
import com.convector.david_000.convector_valute.data.local.DBStayField;
import com.convector.david_000.convector_valute.data.local.SQLDataUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by davidbugayov on 23.09.16.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private SQLDataUtils database;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(DBStayField.NAME_DB);
        database = new SQLDataUtils(getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void shouldAddValuteItem() throws Exception {
        List<ValuteItem>valuteItems=new ArrayList<>();
        ValuteItem valuteItem = new ValuteItem();
        valuteItem.setValuteID(1);
        valuteItem.setNumCode("011");
        valuteItem.setCharCode("RUB");
        valuteItem.setNominal(1);
        valuteItem.setName("Российский рубль");
        valuteItem.setValue("1");
        valuteItems.add(valuteItem);
        database.putData(valuteItems);

        List<ValuteItem> expenseTypes = database.getAllValute();
        assertThat(expenseTypes.size(), is(1));
        assertTrue(expenseTypes.get(0).getValuteID().equals(1));
    }
}