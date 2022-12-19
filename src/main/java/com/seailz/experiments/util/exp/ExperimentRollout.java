package com.seailz.experiments.util.exp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExperimentRollout {

    private int id;
    private final int start;
    private final int end;
    private final int percentage;
    private final boolean enabled;
    private OverrideFilter filter;

    public ExperimentRollout(GuildExperiment experiment, int id) {
        JSONArray mainDataArray =
                experiment.getRaw().getJSONArray(3)
                        .getJSONArray(id);

        JSONArray rolloutArray = mainDataArray.getJSONArray(0)
                .getJSONArray(0);

        JSONObject metadata = rolloutArray.getJSONArray(1)
                .getJSONObject(0);

        this.enabled = rolloutArray.getInt(0) == 1;

        this.start = metadata.getInt("s");
        this.end = metadata.getInt("e");

        try {
            JSONArray filterArray = mainDataArray.getJSONArray(1)
                    .getJSONArray(0)
                    .getJSONArray(1)
                    .getJSONArray(0);
            this.filter = new OverrideFilter(filterArray);
        } catch (JSONException e) {
            this.filter = null;
        }

        int percentage;
        if (this.start == 0 && this.end == 10000) {
            percentage = 100;
        } else {
            percentage = (int) Math.round((this.end - this.start) / 100.0);
        }
        this.percentage = percentage;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("start", start);
        json.put("end", end);
        if (filter != null)
            json.put("filter", filter.toJson());
        json.put("percentage", percentage);
        json.put("enabled", enabled);
        return json;
    }

    public int getId() {
        return id;
    }

    public int getEnd() {
        return end;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getStart() {
        return start;
    }

    public OverrideFilter getFilter() {
        return filter;
    }

}
