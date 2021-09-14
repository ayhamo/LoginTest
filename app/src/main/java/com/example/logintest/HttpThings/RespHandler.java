package com.example.logintest.HttpThings;

//import com.construction.App;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

//import static com.construction.Commons.log;

public abstract class RespHandler extends AsyncHttpResponseHandler {

    public abstract void onSuccess(JPayload payload);

    public abstract void onError(Throwable error);

    public abstract void onMessage(String error);

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//        log("success here..");
        ServerResponse resp = new ServerResponse(responseBody);
        onSuccess(new JPayload(resp.getPayload()));
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


        if (responseBody == null) {
            onError(error);
            return;
        }
        try {
            JSONObject data = new JSONObject(new String(responseBody));
            onMessage(data.getString("message"));
        } catch (JSONException e) {
            onError(error);
        }


    }
}
