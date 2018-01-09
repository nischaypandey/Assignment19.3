package com.example.vibhor.assignment193;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Creating class by extending AppCompatActivity and implementing OnWebServiceResult.
public class MainActivity extends AppCompatActivity implements OnWebServiceResult
{
    //Creating url String,LIst and RecyclerView reference.
    String url = "http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";
    List<DataHandler> model = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    //onCreate method.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //Setting ContentView.

        recyclerView=(RecyclerView)findViewById(R.id.recycler);   //Setting with ID.
        hitRequest();   //Calling method to hit the request.
    }

    //Method to hit the Request.
    private void hitRequest()
    {
        //Adding parameter to FormEncodingBuilder.
        FormEncodingBuilder parameters=new FormEncodingBuilder();
        parameters.add("page","1");

        //Checking the network is connected and executing CallAddr object.
        if(NetworkStatus.getInstance(this).isConnectedToInternet())
        {
            CallAddr call = new CallAddr(this,url,parameters, CommonUtilities.SERVICE_TYPE.GET_DATA,this);
            call.execute();
        }
        else
        {
            Toast.makeText(this,"No Network ! You are offline.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    //Method to get response by API
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type)
    {
        try
        {
            //Creating JSONObjects and Arrays to fetch the information.
            JSONObject obj=new JSONObject(result);
            JSONArray arr=obj.getJSONArray("results");
            for(int i=0;i<arr.length();i++)
            {
                JSONObject jsonObject=arr.getJSONObject(i);
                DataHandler dataHandler= new DataHandler();
                dataHandler.setId(jsonObject.getInt("id"));
                dataHandler.setVote(jsonObject.getInt("vote_count"));
                dataHandler.setName(jsonObject.getString("name"));
                model.add(dataHandler);
            }

            //Creating Adapter for recyclerView.
            DataAdaptor adaptor = new DataAdaptor(this,model);
            recyclerView.setAdapter(adaptor);   //Setting Adapter.
            recyclerView.setLayoutManager(new LinearLayoutManager(this));  //Setting Layout Manager.
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
