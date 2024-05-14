package com.example.astrocast2;

import com.example.astrocast2.LocationAPI.SearchResponse;
import com.example.astrocast2.WeatherAPI.DayQueryResult;
import com.example.astrocast2.WeatherAPI.HourQueryResult;
import com.example.astrocast2.WeatherAPI.RandomImplementation.RandomDayQuery;

import java.util.Scanner;

import com.example.astrocast2.LocationAPI.PlaceSearch;

public class LocationWeatherAPITest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a city name:");
        String location = scanner.nextLine();

        SearchResponse[] response;
        try {
            response = PlaceSearch.queryName(location, 10);
            for (int i = 0; i < response.length; i++) {
                System.out.println(i + ":");
                System.out.println("    Name: " + response[i].getName());
                System.out.println("    Northing: " + response[i].getNorthing());
                System.out.println("    Easting: " + response[i].getEasting());
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.close();
            return;
        }

        System.out.println("Choose result:");
        int result = Integer.parseInt(scanner.nextLine());
        scanner.close();

        float easting = response[result].getEasting();
        float northing = response[result].getNorthing();
        int day = 0;

        DayQueryResult dayQueryResult = new RandomDayQuery().queryDay(easting, northing, day);

        System.out.println(String.format("\nEasting: %f", easting));
        System.out.println(String.format("Northing: %f", northing));
        System.out.println(String.format("Day: %d", day));

        System.out.println("\nMoon Phase Metric: " + dayQueryResult.getMoonPhaseMetric().getDisplayValue());
        System.out.println("Suntime Metric: " + dayQueryResult.getSuntimeMetric().getDisplayValue());

        HourQueryResult[] hourQueryResults = dayQueryResult.getHourQueryResults();
        for (int i = 0; i < hourQueryResults.length; i++) {
            System.out.print(String.format("\nHour %d:00", i));
            System.out.print(" - Cloud Cover Metric: " + hourQueryResults[i].getCloudCoverMetric().getDisplayValue());
            //System.out.println("Moon Altitude Metric: " + hourQueryResults[i].getMoonAltitudeMetric().getDisplayValue()); //dont care enough
            System.out.print(" - Precipitation Metric: " + hourQueryResults[i].getPrecipitationMetric().getDisplayValue());
            System.out.print(" - Temperature Metric: " + hourQueryResults[i].getTemperatureMetric().getDisplayValue());
        }
    }
}
