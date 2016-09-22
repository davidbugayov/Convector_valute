package com.convector.david_000.convector_valute.data.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.convector.david_000.convector_valute.AsyncResponse;
import com.convector.david_000.convector_valute.R;
import com.convector.david_000.convector_valute.data.local.ValuteItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by davidbugayov on 18.09.16.
 */

public class HttpConnection extends AsyncTask<String, Void, String>{
    private Context mContext;
    private AsyncResponse delegate;
    public HttpConnection(Context context,AsyncResponse asyncResponse){
        mContext=context;
        delegate=asyncResponse;
    }

    @Override
    protected String doInBackground(String... path) {

        String content;
        try{
            content = getContent(mContext.getString(R.string.url_rate));
        }
        catch (IOException ex){
            content = ex.getMessage();
        }

        return content;
    }



    @Override
    protected void onPostExecute(String content) {
        delegate.processFinish(content);
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



}
