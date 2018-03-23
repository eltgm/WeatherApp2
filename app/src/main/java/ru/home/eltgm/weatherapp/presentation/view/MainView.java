package ru.home.eltgm.weatherapp.presentation.view;

import java.util.List;

import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 19.03.18
 */

public interface MainView {
    void showWeather(List<Message> weathers);
}
