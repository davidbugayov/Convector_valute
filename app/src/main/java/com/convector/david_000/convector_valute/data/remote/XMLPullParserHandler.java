package com.convector.david_000.convector_valute.data.remote;

import com.convector.david_000.convector_valute.data.local.ValuteItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidbugayov on 19.09.16.
 */


public class XMLPullParserHandler {

    private List<ValuteItem> valuteItems;
    private ValuteItem valuteItem;

    private String text;

    public XMLPullParserHandler(String data) {
        valuteItems = new ArrayList<ValuteItem>();
        parse(data);
    }
    public List<ValuteItem> getValuteItems() {
        return valuteItems;
    }

    private void parse(String stringXML) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
            parser.setInput(new StringReader(stringXML));
            int eventType = parser.getEventType();
            int valuteID=0;
            //добавляем РУБЛИ
            valuteItem = new ValuteItem();
            valuteItem.setValuteID(valuteID);
            valuteItem.setNumCode("011");
            valuteItem.setCharCode("RUB");
            valuteItem.setNominal("1");
            valuteItem.setName("Российский рубль");
            valuteItem.setValue("1");
            valuteItems.add(valuteItem);
            valuteID++;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("Valute")) {
// create a new instance of valute
                            valuteItem = new ValuteItem();
                            valuteItem.setValuteID(valuteID);
                            valuteID++;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("Valute")) {
// add valute object to list
                            valuteItems.add(valuteItem);
                        } else if (tagname.equalsIgnoreCase("NumCode")) {
                            valuteItem.setNumCode(text);
                        } else if (tagname.equalsIgnoreCase("CharCode")) {
                            valuteItem.setCharCode(text);
                        } else if (tagname.equalsIgnoreCase("Nominal")) {
                            valuteItem.setNominal(text);
                        } else if (tagname.equalsIgnoreCase("Name")) {
                            valuteItem.setName(text);
                        } else if (tagname.equalsIgnoreCase("Value")) {
                            valuteItem.setValue(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
