package ru.home.eltgm.weatherapp.presentation.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.List;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.util.DaysOfWeek;

/**
 * Created by eltgm on 23.03.18
 */

public class WeathersAdapter extends RecyclerView.Adapter<WeathersAdapter.ViewHolder> {

    private final ItemClicked clickCallback;

    private int dif = 0;
    private Message weathers;
    private Context context;

    public WeathersAdapter(Context context, ItemClicked clickCallback) {
        this.context = context;
        this.clickCallback = clickCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.day_item_layout, parent, false);

        final ViewHolder h = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ru.home.eltgm.weatherapp.models.weather.List w = weathers.getList().get(pos);
                //TODO обработка нажатия на день
                int pos = h.getAdapterPosition();
                clickCallback.onItemClick(pos + 1);
            }
        });

        return h;
    }

    public void setNull() {
        dif = 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ru.home.eltgm.weatherapp.models.weather.List nightWeather = weathers.getList().get(position + dif);

        Date d = new Date(nightWeather.getDt().longValue() * 1000);
        Calendar c = new GregorianCalendar();
        c.setTime(d);

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


        holder.setDay(dayOfWeek);
        holder.setDate(nightWeather.getDtTxt().substring(6, 10));

        List dayWeather = weathers.getList().get(position + 1 + dif);

        Drawable icon = iconInit(dayWeather.getWeather().get(0).getIcon());


        holder.setDescriptionImage(icon);
        Double night = nightWeather.getMain().getTempMin();
        Double day = dayWeather.getMain().getTempMax();
        holder.setTemp(String.format(Locale.ROOT, "%d/%d", Math.round(day), Math.round(night)));
        dif++;
    }

    private Drawable iconInit(String iconName) {
        Drawable icon = null;

        switch (iconName) {
            case "01d":
                icon = context.getDrawable(R.drawable.o01d);
                break;
            case "02d":
                icon = context.getDrawable(R.drawable.o02d);
                break;
            case "03d":
                icon = context.getDrawable(R.drawable.o03d);
                break;
            case "04d":
                icon = context.getDrawable(R.drawable.o04d);
                break;
            case "09d":
                icon = context.getDrawable(R.drawable.o09d);
                break;
            case "10d":
                icon = context.getDrawable(R.drawable.o10d);
                break;
            case "11d":
                icon = context.getDrawable(R.drawable.o11d);
                break;
            case "13d":
                icon = context.getDrawable(R.drawable.o13d);
                break;
            case "50d":
                icon = context.getDrawable(R.drawable.o50d);
                break;
            case "01n":
                icon = context.getDrawable(R.drawable.o01d);
                break;
            case "02n":
                icon = context.getDrawable(R.drawable.o02d);
                break;
            case "03n":
                icon = context.getDrawable(R.drawable.o03d);
                break;
            case "04n":
                icon = context.getDrawable(R.drawable.o04d);
                break;
            case "09n":
                icon = context.getDrawable(R.drawable.o09d);
                break;
            case "10n":
                icon = context.getDrawable(R.drawable.o10d);
                break;
            case "11n":
                icon = context.getDrawable(R.drawable.o11d);
                break;
            case "13n":
                icon = context.getDrawable(R.drawable.o13d);
                break;
            case "50":
                icon = context.getDrawable(R.drawable.o50d);
                break;
        }

        return icon;
    }

    @Override
    public int getItemCount() {
        if (weathers != null)
            return weathers.getList().size() / 2;
        return 0;
    }

    public void addWeathers(Message message) {
        this.weathers = message;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvDay)
        TextView tvDay;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tvTemp)
        TextView tvTemp;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setDate(String tvDate) {
            this.tvDate.setText(tvDate);
        }

        void setDay(String tvDay) {
            this.tvDay.setText(tvDay);
        }

        void setDescriptionImage(Drawable image) {
            this.imageView.setImageDrawable(image);
        }

        void setTemp(String tvTemp) {
            this.tvTemp.setText(tvTemp);
        }
    }

    public interface ItemClicked {
        void onItemClick(int pos);
    }
}
