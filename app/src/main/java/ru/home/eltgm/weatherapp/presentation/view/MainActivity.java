package ru.home.eltgm.weatherapp.presentation.view;

import android.os.Bundle;
import android.support.v7.widget.ViewUtils;

import com.arellomobile.mvp.MvpAppCompatActivity;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.presentation.util.ViewUtil;

/**
 * Created by eltgm on 19.03.18.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        android.support.v7.widget.Toolbar t = findViewById(R.id.toolbar);
        t.setPadding(ViewUtil.dpToPx(60), ViewUtil.dpToPx(15),0,0);
        t.setTitle("Атепцево");
        t.setSubtitle("обновлено только что");
        t.setSubtitleTextColor(getResources().getColor(R.color.subtitleColor));

    }
}
