package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import ru.home.eltgm.weatherapp.data.database.Database;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

public class DatabaseWeatherDataStore implements WeatherDataStore {
    private final Database database;

    public DatabaseWeatherDataStore(Database database) {
        this.database = database;
    }

    @Override
    public Observable<Message> weathersList(String cityName) {
        return database.getWeather(cityName);
    }

    @Override
    public Observable<java.util.List<List>> dayInfo(int day) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void put(Message message) {
        String cityName = message.getCity().getName();
        if (database.isSaved(cityName))
            database.updateWeather(message);
        else
            database.addWeather(message);
    }

    @Override
    public boolean isCached() {
        return false;
    }

    private final class DatabaseObserver extends DisposableObserver<Message> {

        @Override
        public void onNext(Message message) {
            String cityName = message.getCity().getName();

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
