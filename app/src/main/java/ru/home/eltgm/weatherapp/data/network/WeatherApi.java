package ru.home.eltgm.weatherapp.data.network;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.home.eltgm.weatherapp.models.weather.Message;


/**
 * Created by eltgm on 24.03.18
 */

public interface WeatherApi {
    @GET("/data/2.5/forecast")
    Observable<Message> getWeathers(@Query("q") String city, @Query("lang") String lang, @Query("appid") String appid);
}
