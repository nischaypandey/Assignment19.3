package com.example.vibhor.assignment193;

/**
 * Created by Vibhor on 07-01-2018.
 */

//Interface to fetch the response from the internet.
public interface OnWebServiceResult
{
    //abstract method.
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}