package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.data.network.RestApi;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public class NetworkWeatherDataStore implements WeatherDataStore {

    private RestApi restApi = new RestApi();

    @Override
    public Observable<Message> weathersList() {
        return restApi.getWeather();
    }

    @Override
    public Observable<List> dayInfo(long time) {
        return null;
    }
}
