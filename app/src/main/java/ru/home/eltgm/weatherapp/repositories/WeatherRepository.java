package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public class WeatherRepository {
    private final WeatherDataStore networkWeatherDataStore;
    private final WeatherDataStore cacheWeatherDataStore;

    public WeatherRepository(WeatherDataStore networkWeatherDataStore, WeatherDataStore cacheWeatherDataStore) {
        this.networkWeatherDataStore = networkWeatherDataStore;
        this.cacheWeatherDataStore = cacheWeatherDataStore;
    }

    public Observable<Message> getWeathers(boolean isRefresh) {
        Observable<Message> observable = null;
        if (isRefresh)
            observable = networkWeatherDataStore.weathersList().doOnNext(new Consumer<Message>() {
                @Override
                public void accept(Message message) throws Exception {
                    cacheWeatherDataStore.put(message);
                }
            });

        if (!cacheWeatherDataStore.isCached() && !isRefresh)
            observable = networkWeatherDataStore.weathersList().doOnNext(new Consumer<Message>() {
                @Override
                public void accept(Message message) throws Exception {
                    cacheWeatherDataStore.put(message);
                }
            });
        else if (!isRefresh)
            observable = cacheWeatherDataStore.weathersList();

        return observable;
    }

    public Observable<java.util.List<List>> getNowForecast() {
        return cacheWeatherDataStore.dayInfo(0);
    }
}
