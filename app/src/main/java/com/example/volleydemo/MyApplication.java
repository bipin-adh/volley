package com.example.volleydemo;

import android.app.Application;
import android.content.Context;

import com.example.volleydemo.model.Movie;


public class MyApplication extends Application {

    public static final String API_KEY_THEMOVIEDB = "9da795673e6721fb2225506edd8d78f5";

    public static final String image_Location_url = "https://image.tmdb.org/t/p/w640";


    public static final String backdrop_image_location = "https://image.tmdb.org/t/p/w780";

    private static MyApplication sInstance;

    public static Movie SelectedMovie;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

    }

    public static MyApplication getInstance(){

        return sInstance;
    }

    public static Context getAppContext(){

        return sInstance.getApplicationContext();
    }



}
