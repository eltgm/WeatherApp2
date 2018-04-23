package ru.home.eltgm.weatherapp.data.cache;

import android.util.LruCache;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 27.03.18
 */

public class CacheImpl implements Cache {
    private final LruCache<String, Message> weathersCache;
    private final String KEY = "main";

    public CacheImpl(LruCache<String, Message> cache) {
        this.weathersCache = cache;
    }

    @Override
    public void put(Message value) {
        weathersCache.put(KEY, value);
    }

    @Override
    public Observable<java.util.List<List>> getDayInfo(int day) {
        if (weathersCache.get(KEY) != null) {
            Message m = weathersCache.get(KEY);
            if (day == 0)
                //noinspection unchecked
                return Observable.fromArray(Collections.singletonList(m.getList().get(0)));
            else {
                java.util.List<List> forecast = new ArrayList<>();
                int count = 0;
                List l = m.getList().get(0);
                CharSequence date = l.getDtTxt().substring(0, 10);
                for (int i = 1; i < m.getList().size(); i++) {
                    List data = m.getList().get(i);
                    if (count == day - 1) {
                        for (int j = i; j < 8; j++) {
                            forecast.add(m.getList().get(j));
                        }
                        return Observable.fromArray(forecast);
                    }

                    if (data.getDtTxt().equals(date))
                        continue;
                    else {
                        count++;
                        i += 8;
                        break;
                    }
                }
            }
        }

        return Observable.empty();
    }

    @Override
    public Observable<Message> getAllInfo() {
        return Observable.fromArray(weathersCache.get(KEY));
    }

    @Override
    public boolean isCached() {
        return weathersCache.get(KEY) != null;
    }
}
