package ru.home.eltgm.weatherapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.home.eltgm.weatherapp.di.scopes.RepositoryScope;
import ru.home.eltgm.weatherapp.repositories.CacheWeatherDataStore;
import ru.home.eltgm.weatherapp.repositories.DatabaseWeatherDataStore;
import ru.home.eltgm.weatherapp.repositories.NetworkWeatherDataStore;
import ru.home.eltgm.weatherapp.repositories.WeatherRepository;

/**
 * Created by eltgm on 28.03.18
 */

@Module
public class RepositoryModule {

    @Provides
    @RepositoryScope
    public WeatherRepository provideWeatherRepository(CacheWeatherDataStore cacheWeatherDataStore, NetworkWeatherDataStore networkWeatherDataStore, DatabaseWeatherDataStore databaseWeatherDataStore) {
        return new WeatherRepository(networkWeatherDataStore, cacheWeatherDataStore, databaseWeatherDataStore);
    }
}
