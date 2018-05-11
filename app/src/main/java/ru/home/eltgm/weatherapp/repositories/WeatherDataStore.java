package ru.home.eltgm.weatherapp.repositories;


import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public interface WeatherDataStore {
    Observable<Message> weathersList(String cityName);

    Observable<Message> citiesInfo();

    Observable<java.util.List<List>> dayInfo(String cityName, int day);

    void put(Message message, String cityName);

    boolean isCached(String cityName);
}
