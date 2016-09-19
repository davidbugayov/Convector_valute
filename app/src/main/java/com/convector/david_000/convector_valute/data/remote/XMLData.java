package com.convector.david_000.convector_valute.data.remote;

import android.app.Activity;
import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by gavno on 19.09.16.
 */
public class XMLData {
    Xml xml;
    Context context;
//    XMLData(String xmlData, Context context){
//        xml=this.ge
//    }
//    public String getAllXML(){
//
//        Activity activity = cont;
//        String str = "";
//
//        //For file source
//        //Resources res = activity.getResources();
//        //XmlResourceParser xpp = res.getXml(R.xml.test);
//
//
//        try {
//            //For String source
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(new StringReader(xmlc));
//
//            xpp.next();
//            int eventType = xpp.getEventType();
//
//            while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
//                if (xpp.getEventType()== XmlPullParser.START_TAG) {
//                    if (xpp.getName().equals("cel")) {
//                        str += "\ncell : "+xpp.nextText();
//                    }
//                    if (xpp.getName().equals("val")) {
//                        str += "\nval : "+xpp.nextText();
//                    }
//                }
//                xpp.next();
//            }
//
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return str;
//    }
}
