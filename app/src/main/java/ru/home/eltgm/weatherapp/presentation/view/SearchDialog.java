package ru.home.eltgm.weatherapp.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.presentation.presenter.SearchDialogPresenter;

public class SearchDialog extends MvpAppCompatDialogFragment implements SearchDialogView {

    @InjectPresenter(type = PresenterType.WEAK)
    SearchDialogPresenter mRepositoryLikesPresenter;

    @BindView(R.id.etCityName)
    EditText etCityName;
    @BindView(R.id.bSearch)
    Button bSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.searchdialog_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View view) {
        ButterKnife.bind(this, view);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRepositoryLikesPresenter.searchCity(getContext(), etCityName.getText().toString());
            }
        });

        mRepositoryLikesPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRepositoryLikesPresenter.disconnect();

    }
}
