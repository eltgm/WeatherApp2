package ru.home.eltgm.weatherapp.data.database;


import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.models.weather.Message;

public interface Database {
    void addWeather(Message message);

    Observable<Message> getWeather(String cityName);

    void updateWeather(Message message);

    boolean isSaved(String cityName);
}
