package ru.home.eltgm.weatherapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.home.eltgm.weatherapp.models.weather.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface DayView extends MvpView {
    void showForecast(java.util.List<List> lists);
}
