package ru.home.eltgm.weatherapp.presentation.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.App;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.Screens;
import ru.home.eltgm.weatherapp.presentation.adapters.WeathersAdapter;
import ru.home.eltgm.weatherapp.presentation.presenter.MainPresenter;
import ru.home.eltgm.weatherapp.presentation.util.ViewUtil;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;


/**
 * Created by eltgm on 19.03.18
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {

    Router router = App.INSTANCE.provideRouter();

    @InjectPresenter
    MainPresenter mPresenter;
    private Navigator navigator = new Navigator() {


        @Override
        public void applyCommand(Command command) {
            if (command instanceof Forward) {
                forward((Forward) command);
            } else if (command instanceof Back) {
                back();
            } else {
                Log.e("Cicerone", "Illegal command for this screen: " + command.getClass().getSimpleName());
            }
        }

        private void forward(Forward command) {
            switch (command.getScreenKey()) {
                case Screens.DAY_SCREEN:
                    Intent intent = new Intent(MainActivity.this, DayActivity.class);
                    intent.putExtra("day", (int) command.getTransitionData());
                    startActivity(intent);
                    break;
                default:
                    Log.e("Cicerone", "Unknown screen: " + command.getScreenKey());
                    break;
            }
        }

        private void back() {
            finish();
        }
    };


    @BindView(R.id.rvDays)
    RecyclerView mDays;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.tvMainWeather)
    TextView tvMainWeather;
    @BindView(R.id.tvTemp)
    TextView tvTemp;
    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String cityName;

    private WeathersAdapter weathersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);

        mDays.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        weathersAdapter = new WeathersAdapter(this, new WeathersAdapter.ItemClicked() {
            @Override
            public void onItemClick(int pos) {
                mPresenter.getDayForecast(pos);
            }
        });
        mDays.setAdapter(weathersAdapter);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startRefresh();
            }
        });
        mPresenter.attachView(this);
    }

    private void initToolbar(String cityName) {
        toolbar.setPadding(ViewUtil.dpToPx(5), ViewUtil.dpToPx(15), 0, 0);
        this.cityName = cityName;
        toolbar.setTitle(cityName);
        toolbar.setNavigationIcon(R.drawable.ic_location_on_white_18dp);
        toolbar.setTitleTextAppearance(this, R.style.appbarText);
        toolbar.setSubtitleTextAppearance(this, R.style.appbarSubText);
    }

    @Override
    public void showWeather(Message weathers) {
        initToolbar(weathers.getCity().getName());
        weathersAdapter.setNull();
        weathersAdapter.addWeathers(weathers);
        if (weathers.getDate() == 0)
            toolbar.setSubtitle("Только что");
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM H:mm", Locale.getDefault());
            Date d = new Date(weathers.getDate());
            String updateTime = "Обновлено " + dateFormat.format(new Date(weathers.getDate()));
            toolbar.setSubtitle(updateTime);
        }
    }

    @Override
    public void initDay(List list) {
        tvMainWeather.setText(list.getWeather().get(0).getDescription());
        tvTemp.setText(String.format(Locale.ROOT, "%d°", Math.round(list.getMain().getTemp())));
        ivIcon.setImageDrawable(iconInit(list.getWeather().get(0).getIcon()));
    }

    @Override
    public void startRefresh() {
        swipeContainer.setRefreshing(true);
        mPresenter.onRefresh();
    }

    @Override
    public void stopRefresh() {
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Произошла ошибка - " + error, Toast.LENGTH_SHORT).show();
        Log.d("WTF", "showError: " + error);
    }

    @Override
    public void showDay(List list) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private Drawable iconInit(String iconName) {
        Drawable icon = null;

        switch (iconName) {
            case "01d":
                icon = getDrawable(R.drawable.o01d);
                break;
            case "02d":
                icon = getDrawable(R.drawable.o02d);
                break;
            case "03d":
                icon = getDrawable(R.drawable.o03d);
                break;
            case "04d":
                icon = getDrawable(R.drawable.o04d);
                break;
            case "09d":
                icon = getDrawable(R.drawable.o09d);
                break;
            case "10d":
                icon = getDrawable(R.drawable.o10d);
                break;
            case "11d":
                icon = getDrawable(R.drawable.o11d);
                break;
            case "13d":
                icon = getDrawable(R.drawable.o13d);
                break;
            case "50d":
                icon = getDrawable(R.drawable.o50d);
                break;
            case "01n":
                icon = getDrawable(R.drawable.o01d);
                break;
            case "02n":
                icon = getDrawable(R.drawable.o02d);
                break;
            case "03n":
                icon = getDrawable(R.drawable.o03d);
                break;
            case "04n":
                icon = getDrawable(R.drawable.o04d);
                break;
            case "09n":
                icon = getDrawable(R.drawable.o09d);
                break;
            case "10n":
                icon = getDrawable(R.drawable.o10d);
                break;
            case "11n":
                icon = getDrawable(R.drawable.o11d);
                break;
            case "13n":
                icon = getDrawable(R.drawable.o13d);
                break;
            case "50":
                icon = getDrawable(R.drawable.o50d);
                break;
        }

        return icon;
    }

    @ProvidePresenter
    public MainPresenter createMainPresenter() {
        return new MainPresenter(router);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.INSTANCE.provideNavigatorHolder().removeNavigator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.INSTANCE.provideNavigatorHolder().setNavigator(navigator);
    }
}
