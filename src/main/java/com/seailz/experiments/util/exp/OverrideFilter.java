package com.seailz.experiments.util.exp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class OverrideFilter {

    public List<Object> flags;
    public List<Object> guildIds;

    public OverrideFilter(JSONArray filterRaw) {
        switch (String.valueOf(filterRaw.get(0))) {
            case "1183251248":
                this.flags = filterRaw.getJSONArray(1).toList();
                break;
            case "3013771838":
                this.guildIds = filterRaw.getJSONArray(1).toList();
                break;
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("flags", flags != null ? new JSONArray(flags) : JSONObject.NULL);
        json.put("guild_ids", guildIds != null ? new JSONArray(guildIds) : JSONObject.NULL);
        return json;
    }

}
