package ru.home.eltgm.weatherapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 19.03.18
 */

@StateStrategyType(OneExecutionStateStrategy.class)
public interface MainView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showWeather(Message weathers);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void initDay(List list);

    void startRefresh();

    void stopRefresh();

    void showError(String error);


}
