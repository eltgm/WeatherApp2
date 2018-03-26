package ru.home.eltgm.weatherapp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.observers.DisposableObserver;
import ru.home.eltgm.weatherapp.domain.main.MainInteractor;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.view.MainView;

/**
 * Created by eltgm on 23.03.18
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    private final MainInteractor mainInteractor = new MainInteractor();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        mainInteractor.getWeathers(new WeatherListObserver());
    }

    @Override
    public void disconnect() {
        //TODO отвязка от источиков данных
        mainInteractor.dispose();
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
        }
    }
}
