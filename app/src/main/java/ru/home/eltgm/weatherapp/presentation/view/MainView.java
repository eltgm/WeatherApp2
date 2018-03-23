package ru.home.eltgm.weatherapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 19.03.18
 */

@StateStrategyType(SingleStateStrategy.class)
public interface MainView extends MvpView {

    void showWeather(Message weathers);

    void startRefresh();

    void stopRefresh();

    void showError(String error);
}
