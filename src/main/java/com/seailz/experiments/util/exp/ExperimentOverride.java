package com.seailz.experiments.util.exp;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExperimentOverride {

    public int id;
    public OverrideFilter ovFilter;
    public GuildExperiment experiment;

    public int start;
    public int end;

    public int percentage;

    public ExperimentOverride(GuildExperiment experiment, int next) {
        this.experiment = experiment;

        JSONArray mainDataArray = experiment.getRaw().getJSONArray(5)
                .getJSONArray(0)
                .getJSONArray(next);

        JSONArray requirementsArray = mainDataArray.getJSONArray(1);
        JSONArray rolloutArray = mainDataArray.getJSONArray(0)
                .getJSONArray(0);

        this.id = rolloutArray.getInt(0);

        JSONObject metadata = rolloutArray.getJSONArray(1).getJSONObject(0);
        this.start = metadata.getInt("s");
        this.end = metadata.getInt("e");


        JSONArray filter = requirementsArray
                .getJSONArray(0)
                .getJSONArray(1)
                .getJSONArray(0);

        ovFilter = new OverrideFilter(filter);

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
        json.put("filter", ovFilter.toJson());
        json.put("start", start);
        json.put("end", end);
        //json.put("percentage", percentage);
        return json;
    }

    public int getStart() {
        return start;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }

    public GuildExperiment getExperiment() {
        return experiment;
    }

    public OverrideFilter getOvFilter() {
        return ovFilter;
    }

}
