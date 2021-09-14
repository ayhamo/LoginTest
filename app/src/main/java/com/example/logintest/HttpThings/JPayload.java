package com.example.logintest.HttpThings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JPayload {
    public JPayload(JSONObject payload) {
        this.payload = payload;
    }

    private JSONObject payload;

    public JSONArray asJArray(String k) {
        try {
            return payload.getJSONArray(k);
        } catch (JSONException e) {
            return null;
        }
    }

    public JSONObject asJObject(String k) {
        try {
            return payload.getJSONObject(k);
        } catch (JSONException e) {
            return null;
        }
    }

    public JSONObject get() {
        return payload;
    }

}
