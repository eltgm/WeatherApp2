package ru.home.eltgm.weatherapp.presentation.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.home.eltgm.weatherapp.R;
import ru.home.eltgm.weatherapp.models.weather.Message;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    private final ItemClicked callback;
    private Context context;
    private List<Message> messages = new ArrayList<>();

    public CitiesAdapter(Context context, ItemClicked callback) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cities_item, parent, false);

        final ViewHolder h = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = h.getAdapterPosition();
                callback.onItemClick(pos);
            }
        });

        return h;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message m = messages.get(position);

        Date dateNow = new Date();

        List<ru.home.eltgm.weatherapp.models.weather.List> list = new ArrayList<>(m.getList());

        for (ru.home.eltgm.weatherapp.models.weather.List l :
                m.getList()) {
            if ((l.getDt().longValue() * 1000 - dateNow.getTime()) < 0 || (l.getDt().longValue() * 1000 - dateNow.getTime()) > 10800000L)
                list.remove(l);
        }

        holder.setTvCityName(m.getCity().getName());
        holder.setTvDescr(list.get(0).getWeather().get(0).getDescription());
        holder.setTvTemp(String.valueOf(Math.round(list.get(0).getMain().getTemp())));
        holder.setIvDescr(iconInit(list.get(0).getWeather().get(0).getIcon()));
    }

    public void addCity(Message message) {
        this.messages.add(message);
        notifyItemInserted(this.getItemCount() - 1);
    }

    @Override
    public int getItemCount() {
        return messages.size();
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

    public interface ItemClicked {
        void onItemClick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cvItem)
        CardView cvItem;
        @BindView(R.id.tvCityName)
        TextView tvCityName;
        @BindView(R.id.tvTemp)
        TextView tvTemp;
        @BindView(R.id.tvDescr)
        TextView tvDescr;
        @BindView(R.id.ivDescr)
        ImageView ivDescr;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTvCityName(String tvCityName) {
            this.tvCityName.setText(tvCityName);
        }

        public void setTvTemp(String tvTemp) {
            this.tvTemp.setText(tvTemp);
        }

        public void setTvDescr(String tvDescr) {
            this.tvDescr.setText(tvDescr);
        }

        public void setIvDescr(Drawable ivDescr) {
            this.ivDescr.setImageDrawable(ivDescr);
        }
    }
}
