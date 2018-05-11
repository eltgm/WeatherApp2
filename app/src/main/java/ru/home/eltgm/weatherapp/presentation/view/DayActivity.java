package ru.home.eltgm.weatherapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.App;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Main;
import ru.home.eltgm.weatherapp.presentation.presenter.DayPresenter;
import ru.home.eltgm.weatherapp.presentation.util.DaysOfWeek;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;

public class DayActivity extends MvpAppCompatActivity implements DayView {
    Router router = App.INSTANCE.provideRouter();

    @BindView(R.id.tvDayOfWeek)
    TextView tvDayOfWeek;
    @BindView(R.id.tvTempDay)
    TextView tvTempDay;
    @BindView(R.id.tvTempNight)
    TextView tvTempNight;
    @BindView(R.id.tvDayPressure)
    TextView tvDayPressure;
    @BindView(R.id.tvNightPressure)
    TextView tvNightPressure;
    @BindView(R.id.tvDayHumidity)
    TextView tvDayHumidity;
    @BindView(R.id.tvNightHumidity)
    TextView tvNightHumidity;

    @InjectPresenter
    DayPresenter mPresenter;
    private Navigator navigator = new Navigator() {
        @Override
        public void applyCommand(Command command) {
            if (command instanceof Back) {
                back();
            } else {
                Log.e("Cicerone", "Illegal command for this screen: " + command.getClass().getSimpleName());
            }
        }

        private void back() {
            startActivity(new Intent(DayActivity.this, MainActivity.class));
        }
    };

    @ProvidePresenter
    public DayPresenter createMainPresenter() {
        return new DayPresenter(router, getIntent().getExtras().getBundle("info").getInt("day"), getIntent().getExtras().getBundle("info").getString("cityName"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_day_layout);

        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);


        mPresenter.attachView(this);
    }

    @Override
    public void showForecast(java.util.List<List> lists) {
        Main dayInfo = lists.get(1).getMain();

        Date dd = new Date(lists.get(1).getDt().longValue() * 1000);
        Calendar c = new GregorianCalendar();
        c.setTime(dd);

        String dayOfWeek = null;
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                dayOfWeek = DaysOfWeek.SUNDAY;
                break;
            case 2:
                dayOfWeek = DaysOfWeek.MONDAY;
                break;
            case 3:
                dayOfWeek = DaysOfWeek.TUESDAY;
                break;
            case 4:
                dayOfWeek = DaysOfWeek.WEDNESDAY;
                break;
            case 5:
                dayOfWeek = DaysOfWeek.THURSDAY;
                break;
            case 6:
                dayOfWeek = DaysOfWeek.FRIDAY;
                break;
            case 7:
                dayOfWeek = DaysOfWeek.SATURDAY;
                break;
        }
        Main nightInfo = lists.get(0).getMain();
        String updateTime = dayOfWeek;

        tvDayOfWeek.setText(updateTime);

        tvTempDay.setText(String.format(Locale.ROOT, "%d °C", Math.round(dayInfo.getTemp())));
        tvTempNight.setText(String.format(Locale.ROOT, "%d °C", Math.round(nightInfo.getTemp())));
        tvDayPressure.setText(String.format(Locale.ROOT, "%d", Math.round(dayInfo.getPressure())));
        tvNightPressure.setText(String.format(Locale.ROOT, "%d", Math.round(nightInfo.getPressure())));
        tvDayHumidity.setText(String.format(Locale.ROOT, "%d%c", dayInfo.getHumidity(), '%'));
        tvNightHumidity.setText(String.format(Locale.ROOT, "%d%c", nightInfo.getHumidity(), '%'));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
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
