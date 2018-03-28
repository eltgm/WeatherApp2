package ru.home.eltgm.weatherapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.home.eltgm.weatherapp.data.cache.CacheImpl;
import ru.home.eltgm.weatherapp.data.network.RestApi;
import ru.home.eltgm.weatherapp.di.scopes.DataIMPLScope;
import ru.home.eltgm.weatherapp.repositories.CacheWeatherDataStore;
import ru.home.eltgm.weatherapp.repositories.NetworkWeatherDataStore;

/**
 * Created by eltgm on 28.03.18
 */

@Module
public class DataImplModule {

    @Provides
    @DataIMPLScope
    public CacheWeatherDataStore provideCacheWeatherDataStore(CacheImpl cache) {
        return new CacheWeatherDataStore(cache);
    }

    @Provides
    @DataIMPLScope
    public NetworkWeatherDataStore provideNetworkWeatherDataStore(RestApi restApi) {
        return new NetworkWeatherDataStore(restApi);
    }
}
