package ru.home.eltgm.weatherapp.repositories;

import io.reactivex.Observable;
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
    public Observable<Message> citiesInfo() {
        return database.getAllCities();
    }

    @Override
    public Observable<java.util.List<List>> dayInfo(String cityName, int day) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public void put(Message message, String cityName) {
        if (database.isSaved(cityName))
            database.updateWeather(message);
        else
            database.addWeather(message);
    }

    @Override
    public boolean isCached(String cityName) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
}
