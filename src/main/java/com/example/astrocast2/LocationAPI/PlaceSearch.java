package com.example.astrocast2.LocationAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlaceSearch {
    
    private static final String API_KEY = "dhhN17AOFhqMqag5pEU47NXaurUDBAKF";

    public static void main(String[] args) {
        try {
            SearchResponse[] response = queryName("Cambridge", 5);
            for (int i = 0; i < response.length; i++) {
                System.out.print("Name: " + response[i].getName());
                System.out.print(" Northing: " + response[i].getNorthing());
                System.out.println(" Easting: " + response[i].getEasting());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SearchResponse[] queryName(String name, int numResponses) throws Exception {
        return topResults(getJsonObject(name, numResponses + 5), numResponses);
    }

    private static JSONObject getJsonObject(String search, int maxResults) throws Exception {
        String url = String.format("https://api.os.uk/search/names/v1/find?query=%1$s&key=%2$s&maxresults=%3$s", search, API_KEY, maxResults);
        HttpURLConnection httpClient = (HttpURLConnection) new URI(null, url, null).toURL().openConnection();

        httpClient.setRequestMethod("GET");

        int responseCode = httpClient.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            //System.out.println(response.toString());

            return new JSONObject(response.toString());
        }
    }
    
    /**
     * @param n the max number of entries returned (might be less).
     */
    private static SearchResponse[] topResults(JSONObject jsonResponse, int n) {

        JSONArray entries = jsonResponse.getJSONArray("results");
        HashSet<String> names = new HashSet<String>();

        n = Math.min(n, entries.length());

        ArrayList<SearchResponse> searchResponses = new ArrayList<SearchResponse>();

        for (int i = 0; i < n; i++) {
            JSONObject entry = entries.getJSONObject(i).getJSONObject("GAZETTEER_ENTRY");

            int geometryX = entry.getInt("GEOMETRY_X");
            int geometryY = entry.getInt("GEOMETRY_Y");
            String name1 = 
                entry.getString("NAME1") + " - " +
                entry.getString("LOCAL_TYPE") + " - " +
                entry.getString("COUNTY_UNITARY");

            if (names.contains(name1))
                continue;
            names.add(name1);

            searchResponses.add(new SearchResponse(name1, geometryY, geometryX));
        }
        return searchResponses.toArray(new SearchResponse[0]);
    }
}