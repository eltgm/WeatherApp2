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
        return cache.getAllInfo();
    }

    @Override
    public Observable<java.util.List<List>> dayInfo(int day) {
        return cache.getDayInfo(day);
    }

    @Override
    public void put(Message message) {
        cache.put(message);
    }

    @Override
    public boolean isCached() {
        return cache.isCached();
    }
}
