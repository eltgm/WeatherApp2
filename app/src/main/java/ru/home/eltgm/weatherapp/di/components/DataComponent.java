package ru.home.eltgm.weatherapp.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.home.eltgm.weatherapp.data.cache.CacheImpl;
import ru.home.eltgm.weatherapp.data.database.DbHelper;
import ru.home.eltgm.weatherapp.data.network.RestApi;
import ru.home.eltgm.weatherapp.di.modules.DataModule;
import ru.home.eltgm.weatherapp.di.scopes.DataScope;

/**
 * Created by eltgm on 28.03.18
 */


@Component(dependencies = {AppComponent.class}, modules = {DataModule.class})
@Singleton
@DataScope
public interface DataComponent {
    DbHelper provideDbHelper();

    CacheImpl provideLruCache();

    RestApi provideRetrofit();

    Context provideContext();
}
