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

    @SuppressWarnings("unchecked")
    @Override
    public Observable<java.util.List<List>> getDayInfo(int day) {
        if (weathersCache.get(KEY) != null) {
            Message m = weathersCache.get(KEY);
            if (day == 0)
                //noinspection unchecked
                return Observable.fromArray(Collections.singletonList(m.getList().get(0)));
            else {
                java.util.List<List> forecast = new ArrayList<>();
                int count = -1;
                for (int i = 0; i < day - 1; i++)
                    count++;

                List l = m.getList().get(day + count);
                CharSequence date = l.getDtTxt().substring(0, 10);

                for (List li :
                        m.getList()) {
                    if (li.getDtTxt().startsWith(date.toString()))
                        forecast.add(li);
                }

                return Observable.fromArray(forecast);
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
