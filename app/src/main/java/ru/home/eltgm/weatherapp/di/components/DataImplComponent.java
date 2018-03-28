package ru.home.eltgm.weatherapp.di.components;

import dagger.Component;
import ru.home.eltgm.weatherapp.di.modules.DataImplModule;
import ru.home.eltgm.weatherapp.di.scopes.DataIMPLScope;
import ru.home.eltgm.weatherapp.repositories.CacheWeatherDataStore;
import ru.home.eltgm.weatherapp.repositories.NetworkWeatherDataStore;

/**
 * Created by eltgm on 28.03.18
 */

@Component(dependencies = {DataComponent.class}, modules = {DataImplModule.class})
@DataIMPLScope
public interface DataImplComponent {
    CacheWeatherDataStore provideCacheWeatherDataStore();

    NetworkWeatherDataStore provideNetworkWeatherDataStore();
}
