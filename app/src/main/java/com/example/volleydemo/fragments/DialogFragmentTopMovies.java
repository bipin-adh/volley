package com.example.volleydemo.fragments;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.volleydemo.R;
import com.example.volleydemo.model.Movie;
import com.example.volleydemo.model.TopMovie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.lang.reflect.Type;

import static com.example.volleydemo.extras.Constants.MOVIE_ARGS;

public class DialogFragmentTopMovies extends DialogFragment implements View.OnClickListener{

    public static final String TAG = DialogFragmentMoreInfo.class.getSimpleName();



    private String movieName , movieImageUrl ,movieOverview,movieDate ,movieBackDropImageUrl;
    private float movieRating;


    Button dismissButton;
    TextView textViewMovieTitle , textViewMovieOverview , textViewMovieDate;
    ImageView imageViewPoster , imageViewBackdrop;

    RatingBar ratingBarMovieScore;

    TopMovie movieForDialog;



    private ImageLoader imageLoader;
    private DisplayImageOptions options;


    public DialogFragmentTopMovies(){

    }

    public DialogFragmentTopMovies(Context context){

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(null) // resource or drawable
                .showImageForEmptyUri(null) // resource or drawable
                .showImageOnFail(null)
                .cacheOnDisk(true).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));


    }



    private void initView(View rootView){

        Log.d(TAG, "initView: initview dialog" );
        dismissButton = (Button)rootView.findViewById(R.id.dismissBtn);
        textViewMovieTitle = (TextView) rootView.findViewById(R.id.dialogtextViewTitle);
        textViewMovieOverview=(TextView)rootView.findViewById(R.id.dialogtextViewOverview);
        textViewMovieDate=(TextView)rootView.findViewById(R.id.dialogtextViewDate);
        ratingBarMovieScore = (RatingBar)rootView.findViewById(R.id.dialogRatingBar);
        imageViewPoster = (ImageView)rootView.findViewById(R.id.dialog_poster_image);
        imageViewBackdrop = (ImageView)rootView.findViewById(R.id.dialog_backdrop_image);

    }


    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String  jsonMovieString = null;


        if (getArguments()!= null) {
            jsonMovieString = getArguments().getString(MOVIE_ARGS);
        }

        Type listType = new TypeToken<TopMovie>() {
        }.getType();

        movieForDialog = new Gson().fromJson(jsonMovieString, listType);
        if(movieForDialog!=null){

            movieName = movieForDialog.getTitle();
            movieImageUrl = movieForDialog.getPosterUrl();
            movieOverview = movieForDialog.getOverview();
            movieRating = movieForDialog.getRating();
            movieDate = movieForDialog.getReleaseDate();
            movieBackDropImageUrl = movieForDialog.getBackdropUrl();
            //movieRating = movieForDialog.get
            Log.d(TAG, "onCreateView: movie name is not null" + movieName + movieRating);



        }else{
            Log.d(TAG, "onCreate: moviefordialog is null");
        }
        //if (getArguments() != null) {
        //Get Data from Bundle
        // }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreateView: oncreate view dialog frag after first run");
        View rootView = inflater.inflate(R.layout.moreinfo_diagfrag, null);

        initView(rootView);

        Log.d(TAG, "onCreateView: after initView");
        dismissButton.setOnClickListener(this);

        textViewMovieTitle.setText(movieName);
        textViewMovieOverview.setText(movieOverview);
        textViewMovieDate.setText(movieDate);
        ratingBarMovieScore.setRating(movieRating/2);

        imageLoader.displayImage(movieImageUrl,imageViewPoster, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {




            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

        imageLoader.displayImage(movieBackDropImageUrl, imageViewBackdrop, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });




        return rootView;


    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id) {

            case R.id.dismissBtn:
                dismiss();
                break;

        }


    }


}
