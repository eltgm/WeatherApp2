package ru.home.eltgm.weatherapp.presentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

/**
 * Created by eltgm on 25.03.18
 */

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disconnect();
    }

    public abstract void disconnect();
}
