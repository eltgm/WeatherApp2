package ru.home.eltgm.weatherapp.di.components;

import dagger.Component;
import ru.home.eltgm.weatherapp.di.modules.InteractorModule;
import ru.home.eltgm.weatherapp.di.scopes.InteractorScope;
import ru.home.eltgm.weatherapp.presentation.presenter.DayPresenter;
import ru.home.eltgm.weatherapp.presentation.presenter.MainPresenter;
import ru.home.eltgm.weatherapp.presentation.presenter.SearchPresenter;

/**
 * Created by eltgm on 28.03.18
 */

@Component(dependencies = {RepositoryComponent.class}, modules = {InteractorModule.class})
@InteractorScope
public interface InteractorComponent {
    void inject(MainPresenter presenter);

    void inject(DayPresenter presenter);

    void inject(SearchPresenter presenter);
}
