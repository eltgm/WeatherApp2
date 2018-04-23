package ru.home.eltgm.weatherapp;

import android.app.Application;

import ru.home.eltgm.weatherapp.di.components.AppComponent;
import ru.home.eltgm.weatherapp.di.components.DaggerAppComponent;
import ru.home.eltgm.weatherapp.di.components.DaggerDataComponent;
import ru.home.eltgm.weatherapp.di.components.DaggerDataImplComponent;
import ru.home.eltgm.weatherapp.di.components.DaggerInteractorComponent;
import ru.home.eltgm.weatherapp.di.components.DaggerRepositoryComponent;
import ru.home.eltgm.weatherapp.di.components.DataComponent;
import ru.home.eltgm.weatherapp.di.components.DataImplComponent;
import ru.home.eltgm.weatherapp.di.components.InteractorComponent;
import ru.home.eltgm.weatherapp.di.components.RepositoryComponent;
import ru.home.eltgm.weatherapp.di.modules.AppModule;

/**
 * Created by eltgm on 28.03.18
 */

public class App extends Application {
    private static AppComponent appComponent;
    private static DataComponent dataComponent;
    private static DataImplComponent dataIMPLComponent;
    private static RepositoryComponent repositoryComponent;
    private static InteractorComponent interactorComponent;

    public static InteractorComponent getInteractorComponent() {
        return interactorComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        dataComponent = DaggerDataComponent.builder()
                .appComponent(appComponent)
                .build();

        dataIMPLComponent = DaggerDataImplComponent.builder()
                .dataComponent(dataComponent)
                .build();

        repositoryComponent = DaggerRepositoryComponent.builder()
                .dataImplComponent(dataIMPLComponent)
                .build();

        interactorComponent = DaggerInteractorComponent.builder()
                .repositoryComponent(repositoryComponent)
                .build();
    }
}
