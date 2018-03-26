package ru.home.eltgm.weatherapp.domain.main;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ru.home.eltgm.weatherapp.models.weather.List;
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
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Message, Message>() {
                    @Override
                    public Message apply(Message message) throws Exception {
                        java.util.List<List> weathers = message.getList();
                        java.util.List<List> newWeathers = new ArrayList<>();

                        int count = 0;
                        for (List l :
                                weathers) {
                            if ((l.getDtTxt().subSequence(11, 13).toString().equals("18") || l.getDtTxt().subSequence(11, 13).toString().equals("03")) && count != 0) {
                                if (l.getDtTxt().subSequence(11, 13).toString().equals("18"))
                                    l.setTime(1);
                                else
                                    l.setTime(0);

                                newWeathers.add(l);
                            }
                            count++;
                        }
                        CharSequence ch = newWeathers.get(0).getDtTxt().substring(0, 10);
                        for (int i = 0; i < newWeathers.size(); i++) {
                            if (newWeathers.get(i).getDtTxt().subSequence(0, 10).equals(ch))
                                newWeathers.remove(i);
                        }
                        newWeathers.remove(newWeathers.size() - 1);
                        message.setList(newWeathers);
                        return message;
                    }
                });

        addDisposable(observable
                .subscribeWith(observer));
    }
}
