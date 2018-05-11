package ru.home.eltgm.weatherapp.data.network;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import ru.home.eltgm.weatherapp.models.weather.Message;

/**
 * Created by eltgm on 24.03.18
 */

public class RestApi {
    private static final String API_BASE_URL = "http://api.openweathermap.org";
    private final Retrofit retrofit;

    public RestApi(Retrofit retrofit) {
        OkHttpClient okHttpClient = new OkHttpClient();
        this.retrofit = retrofit;
    }

    public Observable<Message> getWeather(String cityName) {
        WeatherApi api = retrofit.create(WeatherApi.class);
        return api.getWeathers(cityName, "ru", "metric", "96fe47a7f97d2800464e51d37cf3f0f4");
    }
}
