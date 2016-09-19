package com.convector.david_000.convector_valute.url_connection;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.convector.david_000.convector_valute.R;
import com.convector.david_000.convector_valute.data.locale.ValuteItem;
import com.convector.david_000.convector_valute.data.remote.XMLPullParserHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by gavno on 18.09.16.
 */

public class HttpConnection extends AsyncTask<String, Void, String>{
    Activity activity;
    String contentText;
    TextView contentView;
    public HttpConnection(Activity thisIs, String string, TextView textView){
        activity=thisIs;
        contentText=string;
        contentView=textView;
    }

    @Override
    protected String doInBackground(String... path) {

        String content="fuck you";
        try{
                if(checkInternetConnection())
                content = getContent(activity.getString(R.string.url_rate));
        }
        catch (IOException ex){
            content = ex.getMessage();
        }

        return content;
    }
    @Override
    protected void onProgressUpdate(Void... items) {
    }
    @Override
    protected void onPostExecute(String content) {
        contentText=content;
        contentView.setText(content);
        List<ValuteItem> valuteItems = null;
        XMLPullParserHandler parser = new XMLPullParserHandler();
        valuteItems = parser.parse(content);
        int a= valuteItems.size();
        //ArrayAdapter<ValuteItem> adapter = new ArrayAdapter<V>(this,android.R.layout.simple_list_item_1, employees);
        //listView.setAdapter(adapter);
        Toast.makeText(activity, "Данные загружены", Toast.LENGTH_SHORT)
                .show();
    }

    private String getContent(String path) throws IOException {
        BufferedReader reader=null;
        try {
            URL url=new URL(path);
            HttpURLConnection c=(HttpURLConnection)url.openConnection();
            c.connect();
            reader= new BufferedReader(new InputStreamReader(c.getInputStream(),"windows-1251"));
            StringBuilder buf=new StringBuilder();
            String line=null;
            while ((line=reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    protected boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager)activity.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

}


