package com.seailz.experiments.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requester {

    public static JSONObject getRawExperiments() throws IOException {
        // send request to
        // https://discord.com/api/v10/experiments?with_guild_experiments=true
        // and return the response

        URL url = new URL("https://discord.com/api/v10/experiments?with_guild_experiments=true");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        if (con.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        String output;
        StringBuilder sb = new StringBuilder();
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        con.disconnect();
        return new JSONObject(sb.toString());
    }

}
