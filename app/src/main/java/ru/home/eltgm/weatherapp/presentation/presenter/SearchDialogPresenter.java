package ru.home.eltgm.weatherapp.presentation.presenter;

import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;

import ru.home.eltgm.weatherapp.presentation.view.MainActivity;
import ru.home.eltgm.weatherapp.presentation.view.SearchDialogView;

@InjectViewState
public class SearchDialogPresenter extends BasePresenter<SearchDialogView> {

    public void searchCity(Context context, String cityName) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("cityName", cityName);
        context.startActivity(intent);
    }

    @Override
    public void disconnect() {

    }
}
