package com.convector.david_000.convector_valute;

import android.support.test.runner.AndroidJUnit4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(DBStayField.NAME_DB);
        database = new SQLDataUtils(getTargetContext());
    }

    @AfterEach
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