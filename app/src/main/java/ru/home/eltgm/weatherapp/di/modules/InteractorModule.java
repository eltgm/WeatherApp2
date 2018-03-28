package ru.home.eltgm.weatherapp.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.home.eltgm.weatherapp.di.scopes.InteractorScope;
import ru.home.eltgm.weatherapp.domain.main.MainInteractor;
import ru.home.eltgm.weatherapp.repositories.WeatherRepository;

/**
 * Created by eltgm on 28.03.18
 */

@Module
public class InteractorModule {

    @Provides
    @InteractorScope
    public MainInteractor provideMainInteractor(WeatherRepository repository) {
        return new MainInteractor(repository);
    }
}
