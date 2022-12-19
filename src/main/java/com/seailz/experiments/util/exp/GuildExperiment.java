package com.seailz.experiments.util.exp;

import org.apache.commons.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GuildExperiment {

    private final String name;
    private final String niceName;
    private int year;
    private int month;
    private final JSONArray raw;
    private final List<ExperimentOverride> overrides;
    private List<String> overriddenGuilds;
    private final List<ExperimentRollout> treatments;

    public GuildExperiment(JSONArray rawGuildExperiment) {
        this.name = rawGuildExperiment.getString(1);
        this.niceName = WordUtils.capitalize(this.name.replace(this.name.split("_")[0], "").replace("_", " "));
        try {
            this.year =
                    Integer.parseInt(this.name.split("_")[0].split("-")[0]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.year = 0;
        }

        try {
            this.month = Integer.parseInt(this.name.split("_")[0].split("-")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.month = 0;
        }
        this.raw = rawGuildExperiment;

        List<ExperimentOverride> overrides = new ArrayList<>();

        int overrideCount = 0;
        try {
            overrideCount = raw.getJSONArray(5)
                    .getJSONArray(0).length();
        } catch (JSONException ignored) {
        }

        for (int i = 0; i < overrideCount; i++) {
            ExperimentOverride experimentOverride = new ExperimentOverride(this, i);
            overrides.add(experimentOverride);
        }

        this.overrides = overrides;

        List<ExperimentRollout> treatments = new ArrayList<>();
        int treatmentCount = 0;
        try {
            treatmentCount = raw.getJSONArray(3).length();
        } catch (JSONException ignored) {
        }

        for (int i = 0; i < treatmentCount; i++) {
            ExperimentRollout experimentRollout = new ExperimentRollout(this, i);
            treatments.add(experimentRollout);
        }
        this.treatments = treatments;

        List<String> overrideGuilds = new ArrayList<>();
        try {
            JSONArray overrideGuildsRaw = raw.getJSONArray(3);
            if (overrideGuildsRaw.getInt(0) == 1) {
                JSONArray overrideGuildsRawArray = overrideGuildsRaw.getJSONArray(1);
                for (int i = 0; i < overrideGuildsRawArray.length(); i++) {
                    overrideGuilds.add(overrideGuildsRawArray.getString(i));
                }
                this.overriddenGuilds = overrideGuilds;
            }
        } catch (JSONException ignored) {
        }

    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("nice_name", niceName);
        json.put("year", year);
        json.put("month", month);

        if (overrides != null) {
            JSONArray overridesJson = new JSONArray();
            for (ExperimentOverride override : overrides) {
                overridesJson.put(override.toJson());
            }
            json.put("overrides", overridesJson);
        }

        if (overriddenGuilds != null) {
            JSONArray overriddenGuildsJson = new JSONArray();
            for (String guild : overriddenGuilds) {
                overriddenGuildsJson.put(guild);
            }
            json.put("overridden_guilds", overriddenGuildsJson);
        }

        if (treatments != null) {
            JSONArray treatmentsJson = new JSONArray();
            for (ExperimentRollout treatment : treatments) {
                treatmentsJson.put(treatment.toJson());
            }
            json.put("treatments", treatmentsJson);
        }
        return json;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public JSONArray getRaw() {
        return raw;
    }

    public List<ExperimentOverride> getOverrides() {
        return overrides;
    }

    public List<ExperimentRollout> getTreatments() {
        return treatments;
    }

    public List<String> getOverriddenGuilds() {
        return overriddenGuilds;
    }

    public String getName() {
        return name;
    }

    public String getNiceName() {
        return niceName;
    }
}
