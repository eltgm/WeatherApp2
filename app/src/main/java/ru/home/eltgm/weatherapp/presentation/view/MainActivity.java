package ru.home.eltgm.weatherapp.presentation.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.adapters.WeathersAdapter;
import ru.home.eltgm.weatherapp.presentation.presenter.MainPresenter;
import ru.home.eltgm.weatherapp.presentation.util.ViewUtil;

/**
 * Created by eltgm on 19.03.18
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private final WeathersAdapter weathersAdapter = new WeathersAdapter();
    @InjectPresenter
    MainPresenter mPresenter;
    @BindView(R.id.rvDays)
    RecyclerView mDays;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        initToolbar();
        ButterKnife.bind(this);

        mDays.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        mDays.setAdapter(weathersAdapter);
        mPresenter.attachView(this);
    }

    private void initToolbar() {
        Toolbar t = findViewById(R.id.toolbar);
        t.setPadding(ViewUtil.dpToPx(5), ViewUtil.dpToPx(15), 0, 0);
        t.setTitle("Атепцево");
        t.setSubtitle("Обновлено только что");
        t.setNavigationIcon(R.drawable.ic_location_on_white_18dp);
        t.setTitleTextAppearance(this, R.style.appbarText);
        t.setSubtitleTextAppearance(this, R.style.appbarSubText);
    }

    @Override
    public void showWeather(Message weathers) {
        weathersAdapter.addWeathers(weathers);
    }

    @Override
    public void startRefresh() {
        swipeContainer.setRefreshing(true);
    }

    @Override
    public void stopRefresh() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Произошла ошибка - " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
