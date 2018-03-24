package ru.home.eltgm.weatherapp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.observers.DisposableObserver;
import ru.home.eltgm.weatherapp.domain.main.MainInteractor;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.view.MainView;

/**
 * Created by eltgm on 23.03.18
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private final MainInteractor mainInteractor = new MainInteractor();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        mainInteractor.getWeathers(new WeatherListObserver());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainInteractor.dispose();
        //TODO отвязка от источиков данных
    }

    private final class WeatherListObserver extends DisposableObserver<Message> {

        @Override
        public void onNext(Message message) {
            getViewState().showWeather(message);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().showError(e.toString());
        }

        @Override
        public void onComplete() {
            getViewState().stopRefresh();
        }
    }
}
