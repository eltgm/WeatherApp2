package ru.home.eltgm.weatherapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.home.eltgm.weatherapp.models.weather.Message;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface SearchView extends MvpView {

    void showCities(Message cityInfo);
}
