package ru.home.eltgm.weatherapp.presentation.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.presentation.util.ViewUtil;

/**
 * Created by eltgm on 19.03.18
 */

public class MainActivity extends MvpAppCompatActivity implements MainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initViews();
    }

    private void initViews() {
        initToolbar();
    }

    private void initToolbar() {
        android.support.v7.widget.Toolbar t = findViewById(R.id.toolbar);
        t.setPadding(ViewUtil.dpToPx(5), ViewUtil.dpToPx(15), 0, 0);
        t.setTitle("Атепцево");
        t.setSubtitle("Обновлено только что");
        t.setNavigationIcon(R.drawable.ic_location_on_white_18dp);
        t.setTitleTextAppearance(this, R.style.appbarText);
        t.setSubtitleTextAppearance(this, R.style.appbarSubText);
    }
}
