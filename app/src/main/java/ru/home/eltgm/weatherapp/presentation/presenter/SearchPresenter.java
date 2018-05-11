package ru.home.eltgm.weatherapp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.home.eltgm.weatherapp.App;
import ru.home.eltgm.weatherapp.domain.main.MainInteractor;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.view.SearchView;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchView> {

    @Inject
    MainInteractor mainInteractor;

    public SearchPresenter() {
        App.getInteractorComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getCitiesInfo();
    }

    public void showCity(int pos) {

    }

    private void getCitiesInfo() {
        mainInteractor.getCitiesInfo(new CitiesObserver());
    }

    @Override
    public void disconnect() {
        mainInteractor.dispose();
    }

    private final class CitiesObserver extends DisposableObserver<Message> {

        @Override
        public void onNext(Message message) {
            getViewState().showCities(message);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
