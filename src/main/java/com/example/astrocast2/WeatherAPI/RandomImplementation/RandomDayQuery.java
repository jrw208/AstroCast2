package com.example.astrocast2.WeatherAPI.RandomImplementation;

import java.util.stream.IntStream;

import com.example.astrocast2.WeatherAPI.DayQuery;
import com.example.astrocast2.WeatherAPI.DayQueryResult;
import com.example.astrocast2.WeatherAPI.HourQueryResult;

public class RandomDayQuery implements DayQuery {

    @Override
    public DayQueryResult queryDay(float latitude, float longitude, int daysAfterToday) {
        RandomHourQuery hourQuery = new RandomHourQuery();
        return new DayQueryResult(
            IntStream.range(0, 24).mapToObj(x -> hourQuery.queryHour(latitude, longitude, x)).toArray(HourQueryResult[]::new),
            new FixedMoonPhaseMetric(daysAfterToday),
            new FixedSuntimeMetric(daysAfterToday)
        );
    }
}
