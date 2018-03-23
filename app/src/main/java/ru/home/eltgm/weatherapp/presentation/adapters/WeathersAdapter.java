package ru.home.eltgm.weatherapp.presentation.adapters;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.Message;
import ru.home.eltgm.weatherapp.presentation.util.DaysOfWeek;

/**
 * Created by eltgm on 23.03.18
 */

public class WeathersAdapter extends RecyclerView.Adapter<WeathersAdapter.ViewHolder> {

    private Message weathers;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.day_item_layout, parent, false);

        ViewHolder h = new ViewHolder(v);
        final int pos = h.getAdapterPosition();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ru.home.eltgm.weatherapp.models.weather.List w = weathers.getList().get(pos);

                //TODO обработка нажатия на день
            }
        });

        return h;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ru.home.eltgm.weatherapp.models.weather.List weather = weathers.getList().get(position);

        Date d = new Date(weather.getDt().longValue() * 1000);
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
        holder.setDate(weather.getDtTxt().substring(6, 10));
    }

    @Override
    public int getItemCount() {
        if (weathers != null)
            return weathers.getList().size();
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

        public void setDescriptionImage(Drawable image) {
            this.imageView.setImageDrawable(image);
        }

        public void setTemp(String tvTemp) {
            this.tvTemp.setText(tvTemp);
        }
    }
}
