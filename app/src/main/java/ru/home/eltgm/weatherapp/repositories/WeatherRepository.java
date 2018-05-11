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

    public Observable<Message> getWeathers(boolean isRefresh) {
        Observable<Message> observable = null;
        final boolean[] error = {false};
        if (isRefresh)
            observable = networkWeatherDataStore.weathersList("Moscow")
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            error[0] = true;
                        }
                    })
                    .onErrorResumeNext(new ObservableSource<Message>() {
                        @Override
                        public void subscribe(Observer<? super Message> observer) {
                            databaseWeatherDataStore.weathersList("Moscow")
                                    .subscribe(observer);
                        }
                    })
                    .doOnNext(new Consumer<Message>() {
                @Override
                public void accept(Message message) {
                    cacheWeatherDataStore.put(message);
                    if (!error[0])
                        databaseWeatherDataStore.put(message);
                }
            });

        if (!cacheWeatherDataStore.isCached() && !isRefresh)
            observable = networkWeatherDataStore.weathersList("Moscow")
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            error[0] = true;
                        }
                    })
                    .onErrorResumeNext(new ObservableSource<Message>() {
                        @Override
                        public void subscribe(Observer<? super Message> observer) {
                            databaseWeatherDataStore.weathersList("Moscow")
                                    .subscribe(observer);
                        }
                    })
                    .doOnNext(new Consumer<Message>() {
                        @Override
                        public void accept(Message message) {
                            cacheWeatherDataStore.put(message);
                            if (!error[0])
                                databaseWeatherDataStore.put(message);
                }
            });
        else if (!isRefresh)
            observable = cacheWeatherDataStore.weathersList("Moscow");

        return observable;
    }

    public Observable<java.util.List<List>> getNowForecast() {
        return cacheWeatherDataStore.dayInfo(0);
    }

    public Observable<java.util.List<List>> getDayForecast(int day) {
        return cacheWeatherDataStore.dayInfo(day);
    }

    public Observable<Message> getAllCities() {
        return databaseWeatherDataStore.citiesInfo();
    }
}
