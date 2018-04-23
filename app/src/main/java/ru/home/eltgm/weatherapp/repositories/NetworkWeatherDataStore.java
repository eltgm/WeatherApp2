package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.data.network.RestApi;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public class NetworkWeatherDataStore implements WeatherDataStore {

    private final RestApi restApi;

    public NetworkWeatherDataStore(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<Message> weathersList(String cityName) {
        return restApi.getWeather();
    }

    @Override
    public Observable<java.util.List<List>> dayInfo(int day) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void put(Message message) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public boolean isCached() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
}
