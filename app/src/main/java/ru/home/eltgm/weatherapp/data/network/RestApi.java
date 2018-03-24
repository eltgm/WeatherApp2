package ru.home.eltgm.weatherapp.data.network;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public class RestApi {
    private static final String API_BASE_URL = "http://api.openweathermap.org";
    private final Retrofit retrofit;

    public RestApi() {
        OkHttpClient okHttpClient = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public Observable<Message> getWeather() {
        WeatherApi api = retrofit.create(WeatherApi.class);
        return api.getWeathers("Moscow", "ru", "metric", "96fe47a7f97d2800464e51d37cf3f0f4");
    }
}
