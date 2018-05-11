package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.data.cache.Cache;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 27.03.18
 */

public class CacheWeatherDataStore implements WeatherDataStore {
    private final Cache cache;

    public CacheWeatherDataStore(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Observable<Message> weathersList(String cityName) {
        return cache.getAllInfo(cityName);
    }

    @Override
    public Observable<Message> citiesInfo() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<java.util.List<List>> dayInfo(String cityName, int day) {
        return cache.getDayInfo(cityName, day);
    }

    @Override
    public void put(Message message, String cityName) {
        cache.put(message, cityName);
    }

    @Override
    public boolean isCached(String cityName) {
        return cache.isCached(cityName);
    }
}
