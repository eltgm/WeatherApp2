package ru.home.eltgm.weatherapp.domain.main;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableJust;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ru.home.eltgm.weatherapp.models.weather.City;
import ru.home.eltgm.weatherapp.models.weather.Clouds;
import ru.home.eltgm.weatherapp.models.weather.Coord;
import ru.home.eltgm.weatherapp.models.weather.Main;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.models.weather.Snow;
import ru.home.eltgm.weatherapp.models.weather.Sys;
import ru.home.eltgm.weatherapp.models.weather.Weather;
import ru.home.eltgm.weatherapp.models.weather.Wind;

/**
 * Created by eltgm on 23.03.18
 */

public class MainInteractor {

    private final CompositeDisposable disposables;

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
        if (disposables != null)
            disposables.add(disposable);
    }

    public void getWeathers(DisposableObserver<Message> observer) {
        //TODO удалить тест
        Message m1 = new Message();
        m1.setCod("200");
        m1.setMessage(0.0036);
        m1.setCnt(40);
        City c = new City();
        c.setId(524901);
        c.setName("Moscow");
        c.setCountry("none");
        Coord co = new Coord();
        co.setLat(55.7522);
        co.setLon(37.6156);
        c.setCoord(co);
        m1.setCity(c);

        List<ru.home.eltgm.weatherapp.models.weather.List> ebat = new ArrayList<>();
        ru.home.eltgm.weatherapp.models.weather.List l = new ru.home.eltgm.weatherapp.models.weather.List();
        l.setDt(1521828000);
        l.setDtTxt("2018-03-23 18:00:00");
        Main m = new Main();
        m.setTemp((double) Math.round(-0.76));
        m.setTempMin((double) Math.round(-0.96));
        m.setTempMax((double) Math.round(-0.76));
        m.setPressure((double) Math.round(992.02));
        m.setSeaLevel((double) Math.round(1012.43));
        m.setGrndLevel((double) Math.round(992.02));
        m.setHumidity(89);
        m.setTempKf(0.2);
        l.setMain(m);
        List<Weather> weatherList = new ArrayList<>();
        Weather w = new Weather();
        w.setId(600);
        w.setMain("Snow");
        w.setDescription("небольшой снегопад");
        w.setIcon("13d");
        weatherList.add(w);
        l.setWeather(weatherList);
        Clouds cl = new Clouds();
        cl.setAll(76);
        l.setClouds(cl);
        Wind win = new Wind();
        win.setDeg((double) 351);
        win.setSpeed(3.76);
        l.setWind(win);
        Snow s = new Snow();
        s.set3h(0.141);
        l.setSnow(s);
        Sys sy = new Sys();
        sy.setPod("n");
        l.setSys(sy);

        ru.home.eltgm.weatherapp.models.weather.List l2 = new ru.home.eltgm.weatherapp.models.weather.List();
        l2.setDt(1521914400);
        l2.setDtTxt("2018-03-24 18:00:00");
        Main m2 = new Main();
        m2.setTemp((double) Math.round(-0.76));
        m2.setTempMin((double) Math.round(-0.96));
        m2.setTempMax((double) Math.round(-0.76));
        m2.setPressure((double) Math.round(992.02));
        m2.setSeaLevel((double) Math.round(1012.43));
        m2.setGrndLevel((double) Math.round(992.02));
        m2.setHumidity(89);
        m2.setTempKf(0.2);
        l2.setMain(m2);
        List<Weather> weatherList1 = new ArrayList<>();
        Weather w1 = new Weather();
        w1.setId(600);
        w1.setMain("Snow");
        w1.setDescription("небольшой снегопад");
        w1.setIcon("13d");
        weatherList1.add(w1);
        l2.setWeather(weatherList1);
        Clouds cl1 = new Clouds();
        cl1.setAll(76);
        l2.setClouds(cl1);
        Wind win1 = new Wind();
        win1.setDeg((double) 351);
        win1.setSpeed(3.76);
        l2.setWind(win1);
        Snow s1 = new Snow();
        s1.set3h(0.141);
        l2.setSnow(s1);
        Sys sy1 = new Sys();
        sy1.setPod("n");
        l2.setSys(sy1);
        ebat.add(l);
        ebat.add(l2);

        m1.setList(ebat);
        final Observable<Message> observable = new ObservableJust<>(m1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
