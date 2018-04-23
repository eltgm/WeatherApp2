package ru.home.eltgm.weatherapp.di.components;

import android.content.Context;

import dagger.Component;
import ru.home.eltgm.weatherapp.di.modules.AppModule;

@Component(modules = {AppModule.class})
public interface AppComponent {
    Context provideContext();
}
