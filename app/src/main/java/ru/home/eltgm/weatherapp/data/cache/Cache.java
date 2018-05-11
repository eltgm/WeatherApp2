package ru.home.eltgm.weatherapp.data.cache;

import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 27.03.18
 */

public interface Cache {
    void put(Message value, String cityName);

    Observable<java.util.List<List>> getDayInfo(String cityName, int day);

    Observable<Message> getAllInfo(String cityName);

    boolean isCached(String cityName);
}
