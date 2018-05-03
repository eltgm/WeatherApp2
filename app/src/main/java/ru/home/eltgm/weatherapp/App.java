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
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by eltgm on 28.03.18
 */

public class App extends Application {
    private static AppComponent appComponent;
    private static DataComponent dataComponent;
    private static DataImplComponent dataIMPLComponent;
    private static RepositoryComponent repositoryComponent;
    private static InteractorComponent interactorComponent;

    public static App INSTANCE;
    private Cicerone<Router> cicerone = Cicerone.create();

    public static InteractorComponent getInteractorComponent() {
        return interactorComponent;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
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

    public Router provideRouter() {
        return cicerone.getRouter();
    }

    public NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
