package com.example.astrocast2.LocationAPI;

public class SearchResponse {
    private String name;
    private int northing;
    private int easting;

    public SearchResponse(String name, int northing, int easting) {
        this.name = name;
        this.northing = northing;
        this.easting = easting;
    }

    public String getName() {
        return name;
    }

    public float getNorthing() {
        return northing;
    }

    public float getEasting() {
        return easting;
    }
}
