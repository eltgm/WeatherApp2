package ru.home.eltgm.weatherapp.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import io.reactivex.Observable;
import ru.home.eltgm.weatherapp.models.weather.City;
import ru.home.eltgm.weatherapp.models.weather.Message;

public class DbHelper extends SQLiteOpenHelper implements Database {
    private static final int DATABASE_VERSION = 1;
    private final String WEATHERS_TABLE = "weathersTable";
    private final Context mContext;

    public DbHelper(Context context) {
        super(context, "weatherDB.db", null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + WEATHERS_TABLE +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "CITY_NAME TEXT," +
                "CITY_WEATHER TEXT," +
                "DATE INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WEATHERS_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    @Override
    public void addWeather(Message message) {
        ContentValues cv = new ContentValues();

        Gson gson = new Gson();
        String weatherJSON = gson.toJson(message.getList());

        cv.put("CITY_NAME", message.getCity().getName());
        cv.put("CITY_WEATHER", weatherJSON);
        cv.put("DATE", System.currentTimeMillis());
        SQLiteDatabase database = this.getWritableDatabase();
        long v = database.insert(WEATHERS_TABLE,
                null,
                cv);
    }

    @Override
    public Observable<Message> getWeather(String cityName) {
        Message message = new Message();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.query(WEATHERS_TABLE,
                new String[]{"_id", "CITY_WEATHER", "CITY_NAME", "DATE"},
                "CITY_NAME = ?",
                new String[]{cityName},
                null, null, null);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("_id");
            int cityNameIndex = c.getColumnIndex("CITY_NAME");
            int cityWeatherIndex = c.getColumnIndex("CITY_WEATHER");
            int dateIndex = c.getColumnIndex("DATE");

            do {
                City city = new City();
                city.setName(c.getString(cityNameIndex));
                message.setCity(city);
                Gson gson = new Gson();
                message.setList(gson.fromJson(c.getString(cityWeatherIndex), ru.home.eltgm.weatherapp.models.weather.List.ListList.class));
                message.setDate(c.getLong(dateIndex));
            } while (c.moveToNext());
        }

        c.close();

        return Observable.fromArray(message);
    }

    @Override
    public Observable<Message> getAllCities() {
        Message message = new Message();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.query(WEATHERS_TABLE,
                new String[]{"_id", "CITY_WEATHER", "CITY_NAME", "DATE"},
                null,
                null,
                null, null, null);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("_id");
            int cityNameIndex = c.getColumnIndex("CITY_NAME");
            int cityWeatherIndex = c.getColumnIndex("CITY_WEATHER");
            int dateIndex = c.getColumnIndex("DATE");

            do {
                City city = new City();
                city.setName(c.getString(cityNameIndex));
                message.setCity(city);
                Gson gson = new Gson();
                message.setList(gson.fromJson(c.getString(cityWeatherIndex), ru.home.eltgm.weatherapp.models.weather.List.ListList.class));
                message.setDate(c.getLong(dateIndex));
            } while (c.moveToNext());
        }

        c.close();

        return Observable.fromArray(message);
    }

    @Override
    public void updateWeather(Message message) {
        ContentValues cv = new ContentValues();

        Gson gson = new Gson();
        String weatherJSON = gson.toJson(message.getList());

        cv.put("CITY_NAME", message.getCity().getName());
        cv.put("CITY_WEATHER", weatherJSON);
        cv.put("DATE", System.currentTimeMillis());
        SQLiteDatabase database = this.getWritableDatabase();
        database.update(WEATHERS_TABLE,
                cv, "CITY_NAME = ?",
                new String[]{message.getCity().getName()});
    }

    @Override
    public boolean isSaved(String cityName) {
        Message message = new Message();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.query(WEATHERS_TABLE,
                new String[]{"_id", "CITY_WEATHER", "CITY_NAME", "DATE"},
                "CITY_NAME = ?",
                new String[]{cityName},
                null, null, null);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("_id");
            int cityNameIndex = c.getColumnIndex("CITY_NAME");
            int cityWeatherIndex = c.getColumnIndex("CITY_WEATHER");
            int dateIndex = c.getColumnIndex("DATE");

            do {

                City city = new City();
                city.setName(c.getString(cityNameIndex));
                message.setCity(city);
                Gson gson = new Gson();
                message.setList(gson.fromJson(c.getString(cityWeatherIndex), ru.home.eltgm.weatherapp.models.weather.List.ListList.class));
                message.setDate(c.getLong(dateIndex));
            } while (c.moveToNext());
        }

        c.close();

        return message.getCity() != null;

    }
}
