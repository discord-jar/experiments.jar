package com.seailz.experiments;

import com.seailz.experiments.util.Requester;
import com.seailz.experiments.util.exp.GuildExperiment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seailz
 */
public class DiscordExperiments {

    /**
     * Returns a list of all guild experiments that are currently running.
     * A request is first sent to the Discord API to get the raw data, and then the data is parsed
     * and returned as a list of {@link GuildExperiment} objects.
     *
     * @return A list of all guild experiments that are currently running.
     * @throws IOException If the request to the Discord API fails.
     */
    public static List<GuildExperiment> getExperiments() throws IOException {
        JSONArray rawGuildExperiments = Requester.getRawExperiments().getJSONArray("guild_experiments");
        List<GuildExperiment> guildExperiments = new ArrayList<>();

        for (int i = 0; i < rawGuildExperiments.length(); i++) {
            if (rawGuildExperiments.getJSONArray(i).get(1).equals(JSONObject.NULL))
                continue;
            guildExperiments.add(new GuildExperiment(rawGuildExperiments.getJSONArray(i)));
        }
        return guildExperiments;
    }
}