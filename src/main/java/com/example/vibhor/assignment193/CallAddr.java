package com.example.vibhor.assignment193;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okio.Buffer;

/**
 * Created by Vibhor on 07-01-2018.
 */

//Class extending AsyncTask class to fetch the response from API in backgroud
public class CallAddr extends AsyncTask<String,Void,String>
{
    //Creating Fields
    Context context;
    String result="";
    FormEncodingBuilder formBody;
    String url;
    OnWebServiceResult resultListener;
    CommonUtilities.SERVICE_TYPE Servicetype;
    Request request;

    //Method to get the request.
    public Request getRequest()
    {
        return request;
    }

    //Constructor.
    public CallAddr(Context context, String url, FormEncodingBuilder formBody, CommonUtilities.SERVICE_TYPE Servicetype, OnWebServiceResult resultListener)
    {
        this.context = context;
        this.formBody = formBody;
        this.url = url;
        this.resultListener = resultListener;
        this.Servicetype = Servicetype;
    }

    @Override
    //Method which is called first.
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    //Method which will work in background as a different thread.
    protected String doInBackground(String... params)
    {
        //Setting Time out.
        OkHttpClient client=new OkHttpClient();
        client.setConnectTimeout(120, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(120, TimeUnit.SECONDS); // socket timeout

        //Requesting information.
        RequestBody body=formBody.build();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try
        {
            //fetching response.
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful())
            {
                result=response.toString();
            }
            result=response.body().string();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    //Method which will execute last.
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        resultListener.getWebResponse(s,Servicetype);
    }

    //Method to convert String from buffer.
    private  static String bodyToString(final Request request)
    {
        try
        {
            final Request copy=request.newBuilder().build();
            final Buffer buffer=new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        }
        catch (IOException e)
        {
            return "did not worked";
        }
    }
}