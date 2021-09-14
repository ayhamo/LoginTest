package com.example.logintest.HttpThings;

import android.app.Activity;
import android.content.Intent;

//import com.construction.Activities.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
//import static com.construction.App.getDb;

public class ServerResponse {
    JSONObject data;
    boolean ok = false;

    public ServerResponse(byte[] data) {
        this(new String(data), true);
    }

    public ServerResponse(String data) {
        this(data, true);
    }

    public ServerResponse(String data, boolean log) {
        try {
            this.data = new JSONObject(data);
//            ok = this.data.getString("status").equals("ok");
//            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerResponse(byte[] data, boolean log) {
        this(new String(data), log);
    }

    private void check() {
        if (!ok) {
            if (getMessage().equals("not authenticated")) {
                System.out.println("\"not authenticated\"");
            }
        }
    }

    public JSONObject getPayload() {
        try {
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject obj = new JSONObject();
            try {
                obj.put("message", data.getString("message"));
                return obj;
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public boolean ok() {
        return ok;
    }

    public String getAll() {
        try {
            return data != null ? data.toString(4) : "NULL RESPONSE !";
        } catch (JSONException e) {
            e.printStackTrace();
            return "NULL";
        }
    }

    public String getMessage() {
        try {
            return data.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
