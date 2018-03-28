package ru.home.eltgm.weatherapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import ru.home.eltgm.weatherapp.data.cache.CacheImpl;
import ru.home.eltgm.weatherapp.data.network.RestApi;
import ru.home.eltgm.weatherapp.di.modules.DataModule;

/**
 * Created by eltgm on 28.03.18
 */


@Component(modules = {DataModule.class})
@Singleton
public interface DataComponent {

    CacheImpl provideLruCache();

    RestApi provideRetrofit();
}
