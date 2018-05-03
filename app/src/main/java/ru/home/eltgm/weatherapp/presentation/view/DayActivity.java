package ru.home.eltgm.weatherapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.App;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.presentation.presenter.DayPresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;

public class DayActivity extends MvpAppCompatActivity implements DayView {
    Router router = App.INSTANCE.provideRouter();

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
        return new DayPresenter(router, getIntent().getIntExtra("day", 0));
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
