package ru.home.eltgm.weatherapp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.home.eltgm.weatherapp.App;
import ru.home.eltgm.weatherapp.domain.main.MainInteractor;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.presentation.view.DayView;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class DayPresenter extends BasePresenter<DayView> {

    private final Router router;
    @Inject
    MainInteractor mainInteractor;
    private int day;
    private final String cityName;

    public DayPresenter(Router router, int day, String cityName) {
        App.getInteractorComponent().inject(this);
        this.router = router;
        this.day = day;
        this.cityName = cityName;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        mainInteractor.getDayInfo(new DayInfoObserver(), day, cityName);
    }

    @Override
    public void disconnect() {
        mainInteractor.dispose();
    }

    private final class DayInfoObserver extends DisposableObserver<java.util.List<List>> {

        @Override
        public void onNext(java.util.List<List> lists) {
            getViewState().showForecast(lists);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
