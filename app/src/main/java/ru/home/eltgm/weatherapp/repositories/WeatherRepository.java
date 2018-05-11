package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public class WeatherRepository {
    private final WeatherDataStore networkWeatherDataStore;
    private final WeatherDataStore cacheWeatherDataStore;
    private final WeatherDataStore databaseWeatherDataStore;

    public WeatherRepository(WeatherDataStore networkWeatherDataStore, WeatherDataStore cacheWeatherDataStore, WeatherDataStore databaseWeatherDataStore) {
        this.networkWeatherDataStore = networkWeatherDataStore;
        this.cacheWeatherDataStore = cacheWeatherDataStore;
        this.databaseWeatherDataStore = databaseWeatherDataStore;
    }

    public Observable<Message> getWeathers(boolean isRefresh, final String cityName) {
        Observable<Message> observable = null;
        final boolean[] error = {false};
        if (isRefresh)
            observable = networkWeatherDataStore.weathersList(cityName)
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            error[0] = true;
                        }
                    })
                    .onErrorResumeNext(new ObservableSource<Message>() {
                        @Override
                        public void subscribe(Observer<? super Message> observer) {
                            databaseWeatherDataStore.weathersList(cityName)
                                    .subscribe(observer);
                        }
                    })
                    .doOnNext(new Consumer<Message>() {
                @Override
                public void accept(Message message) {
                    cacheWeatherDataStore.put(message, cityName);
                    if (!error[0])
                        databaseWeatherDataStore.put(message, cityName);
                }
            });

        if (!cacheWeatherDataStore.isCached(cityName) && !isRefresh)
            observable = networkWeatherDataStore.weathersList(cityName)
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            error[0] = true;
                        }
                    })
                    .onErrorResumeNext(new ObservableSource<Message>() {
                        @Override
                        public void subscribe(Observer<? super Message> observer) {
                            databaseWeatherDataStore.weathersList(cityName)
                                    .subscribe(observer);
                        }
                    })
                    .doOnNext(new Consumer<Message>() {
                        @Override
                        public void accept(Message message) {
                            cacheWeatherDataStore.put(message, cityName);
                            if (!error[0])
                                databaseWeatherDataStore.put(message, cityName);
                }
            });
        else if (!isRefresh)
            observable = cacheWeatherDataStore.weathersList(cityName);

        return observable;
    }

    public Observable<java.util.List<List>> getNowForecast(String cityName) {
        return cacheWeatherDataStore.dayInfo(cityName, 0);
    }

    public Observable<java.util.List<List>> getDayForecast(String cityName, int day) {
        return cacheWeatherDataStore.dayInfo(cityName, day);
    }

    public Observable<Message> getAllCities() {
        return databaseWeatherDataStore.citiesInfo();
    }
}
