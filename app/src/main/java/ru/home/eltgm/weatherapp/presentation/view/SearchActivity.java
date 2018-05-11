package ru.home.eltgm.weatherapp.presentation.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.adapters.CitiesAdapter;
import ru.home.eltgm.weatherapp.presentation.presenter.SearchPresenter;

public class SearchActivity extends MvpAppCompatActivity implements SearchView {

    @InjectPresenter
    SearchPresenter mPresenter;

    @BindView(R.id.toolbar2)
    Toolbar toolbar;
    @BindView(R.id.rvCities)
    RecyclerView rvCities;

    private CitiesAdapter citiesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        citiesAdapter = new CitiesAdapter(this, new CitiesAdapter.ItemClicked() {
            @Override
            public void onItemClick(String cityName) {
                mPresenter.showCity(SearchActivity.this, cityName);
            }
        });
        rvCities.setAdapter(citiesAdapter);

        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disconnect();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showCities(Message cityInfo) {
        citiesAdapter.addCity(cityInfo);
    }
}
