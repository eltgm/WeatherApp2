package ru.home.eltgm.weatherapp.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import ru.home.eltgm.weatherapp.App;
import ru.home.eltgm.weatherapp.domain.main.MainInteractor;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.view.MainActivity;
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

    public void showCity(Context context, String cityName) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("cityName", cityName);
        context.startActivity(intent);
        Log.d("CITY_NAME", "showCity: " + cityName);
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
