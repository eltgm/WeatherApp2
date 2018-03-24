package ru.home.eltgm.weatherapp.domain.main;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.repositories.WeatherRepository;

/**
 * Created by eltgm on 23.03.18
 */

public class MainInteractor {

    private final CompositeDisposable disposables;
    private WeatherRepository weatherRepository = new WeatherRepository();

    public MainInteractor() {
        this.disposables = new CompositeDisposable();
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void getWeathers(DisposableObserver<Message> observer) {
        final Observable<Message> observable = weatherRepository.getWeathers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        addDisposable(observable

                .subscribeWith(observer));
    }
}
