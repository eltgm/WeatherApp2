package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public class WeatherRepository {
    private WeatherDataStore networkWeatherDataStore = new NetworkWeatherDataStore();

    public Observable<Message> getWeathers() {
        return networkWeatherDataStore.weathersList().doOnNext(new Consumer<Message>() {
            @Override
            public void accept(Message message) throws Exception {

            }
        });
    }
}
