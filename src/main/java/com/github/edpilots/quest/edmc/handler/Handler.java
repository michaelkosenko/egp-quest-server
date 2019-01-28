package com.github.edpilots.quest.edmc.handler;

import org.json.JSONException;
import org.json.JSONObject;

public interface Handler {
    void handle(JSONObject json);
}
