package com.convector.david_000.convector_valute.data.remote;

import android.app.Activity;
import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gavno on 19.09.16.
 */
public class XMLData {
    Xml xml;
    Context context;



    public  XMLData (String data)
            throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        int i=0;
        ArrayList<Map<String,String>>q1=new ArrayList<>();


        xpp.setInput( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            Map<String,String> fedya= new HashMap<>();
            if(eventType == XmlPullParser.START_DOCUMENT) {
                Log.e("xml","Start document");
            } else if(eventType == XmlPullParser.START_TAG) {
                if(xpp.getName().equals("NumCode")){
                    fedya.put("Valute_ID",String.valueOf(i));
                    fedya.put("NumCode",xpp.getText());
                }else if(xpp.getName().equals("CharCode")){
                    fedya.put("CharCode",xpp.getText());
                }else if(xpp.getName().equals("Nominal")){
                    fedya.put("Nominal",xpp.getText());
                }else if (xpp.getName().equals("Name")){
                    fedya.put("Name",xpp.getText());
                }else if (xpp.getName().equals("Value")){
                    fedya.put("Value",xpp.getText());
                    q1.add(fedya);
                    i++;
                }
                Log.e("xml","Start tag "+xpp.getName());
            } else if(eventType == XmlPullParser.END_TAG) {
                Log.e("xml","End tag "+xpp.getName());
            } else if(eventType == XmlPullParser.TEXT) {
                Log.e("xml","Text "+xpp.getText());
            }

            eventType = xpp.next();
        }
        Log.e("xml","End document");
        int a=q1.size();
    }
}
