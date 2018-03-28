package ru.home.eltgm.weatherapp.di.components;

import dagger.Component;
import ru.home.eltgm.weatherapp.di.modules.RepositoryModule;
import ru.home.eltgm.weatherapp.di.scopes.RepositoryScope;
import ru.home.eltgm.weatherapp.repositories.WeatherRepository;

/**
 * Created by eltgm on 28.03.18
 */

@Component(dependencies = {DataImplComponent.class}, modules = {RepositoryModule.class})
@RepositoryScope
public interface RepositoryComponent {
    WeatherRepository provideWeatherRepository();
}
