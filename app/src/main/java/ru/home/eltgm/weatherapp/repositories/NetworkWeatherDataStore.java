package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
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
        return restApi.getWeather(cityName)
                .doOnNext(new Consumer<Message>() {
                    @Override
                    public void accept(Message message) {
                        message.setDate(0);
                    }
                });
    }

    @Override
    public Observable<Message> citiesInfo() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<java.util.List<List>> dayInfo(String cityName, int day) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void put(Message message, String cityName) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public boolean isCached(String cityName) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
}
