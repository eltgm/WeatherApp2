package ru.home.eltgm.weatherapp.di.modules;

import android.util.LruCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.home.eltgm.weatherapp.data.cache.CacheImpl;
import ru.home.eltgm.weatherapp.data.network.RestApi;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 28.03.18
 */

@Module
public class DataModule {
    private static final String API_BASE_URL = "http://api.openweathermap.org";

    @Singleton
    @Provides
    public CacheImpl provideCache() {
        return new CacheImpl(new LruCache<String, Message>(1));
    }

    @Provides
    public RestApi provideRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return new RestApi(new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build());
    }
}
