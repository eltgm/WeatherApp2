package ru.home.eltgm.weatherapp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.home.eltgm.weatherapp.App;
import ru.home.eltgm.weatherapp.domain.main.MainInteractor;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.Screens;
import ru.home.eltgm.weatherapp.presentation.view.MainView;
import ru.terrakok.cicerone.Router;

/**
 * Created by eltgm on 23.03.18
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    MainInteractor mainInteractor;
    private boolean isRefresh = false;

    private final Router router;

    public MainPresenter(Router router) {
        App.getInteractorComponent().inject(this);
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        mainInteractor.getWeathers(new WeatherListObserver(), isRefresh);
    }

    @Override
    public void disconnect() {
        isRefresh = false;
        mainInteractor.dispose();
    }

    public void onRefresh() {
        isRefresh = true;
        mainInteractor.getWeathers(new WeatherListObserver(), isRefresh);
    }

    private void getNowForecast() {
        mainInteractor.getNowInfo(new NowInfoObserver());
    }

    public void getDayForecast(final int day) {
        router.navigateTo(Screens.DAY_SCREEN, day);
    }

    private final class WeatherListObserver extends DisposableObserver<Message> {

        @Override
        public void onNext(Message message) {
            getViewState().showWeather(message);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().showError(e.toString());
            getViewState().stopRefresh();
        }

        @Override
        public void onComplete() {
            getViewState().stopRefresh();
            getNowForecast();
            isRefresh = false;
        }
    }

    private final class NowInfoObserver extends DisposableObserver<List<ru.home.eltgm.weatherapp.models.weather.List>> {

        @Override
        public void onNext(List<ru.home.eltgm.weatherapp.models.weather.List> lists) {
            getViewState().initDay(lists.get(0));
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
