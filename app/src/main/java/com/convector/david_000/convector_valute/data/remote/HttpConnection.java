package com.convector.david_000.convector_valute.data.remote;

import android.content.Context;

import com.convector.david_000.convector_valute.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gavno on 18.09.16.
 */

public class HttpConnection {
    private Context mContext;
    public HttpConnection(Context context){
        mContext=context;
    }
    public String getContent(){
        String content;
        try{
            content = getXmlFromUrl(mContext.getString(R.string.url_rate));
        }
        catch (IOException ex){
            content = ex.getMessage();
        }
        return content;
    }

    private String getXmlFromUrl(String path) throws IOException {
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



}


