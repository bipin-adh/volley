package com.example.volleydemo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.volleydemo.API.ApiClient;
import com.example.volleydemo.API.ApiInterface;
import com.example.volleydemo.MyApplication;
import com.example.volleydemo.R;
import com.example.volleydemo.adapters.BoxOfficeAdapter;
import com.example.volleydemo.adapters.TopMoviesAdapter;
import com.example.volleydemo.model.MoviesResponse;
import com.example.volleydemo.model.TopMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopMoviesFragment extends Fragment {

    public static final String TAG = TopMoviesFragment.class.getSimpleName();

    private ImageLoader imageLoader;


    RecyclerView mRecyclerView;

    private TopMoviesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;




    public static final String URL_THEMOVIEDB = "https://api.themoviedb.org/3/movie/popular";
    public static final String MOVIE_ARGS = "my_movie";

    private final static String API_KEY = "9da795673e6721fb2225506edd8d78f5";


    


    public TopMoviesFragment(){

    }

    private void initView(View view) {

        Log.d(TAG, "initView: TopMoviesFragment");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerviewBoxOffice);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);



    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: inside onCreate");

        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            Log.d(TAG, "onCreate: API KEY empty");
            return;
        }
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG, "onCreate: api service created");

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);

        Log.d(TAG, "onCreate:  Call<MoviesResponse> call");
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                Log.d(TAG, "onResponse: inside on response");
                List<TopMovie> movies = response.body().getResults();
                Log.d(TAG, "onResponse: retrofit data" + movies);
                mAdapter = new TopMoviesAdapter(getActivity(),R.layout.content_fragment_boxoffice,movies);
                mRecyclerView.setAdapter(mAdapter);

                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                Log.e(TAG,"error "+ t.toString());
            }

        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: TopMoviesFragment");
        View view = inflater.inflate(R.layout.fragment_boxoffice, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        initView(view);
        Log.d(TAG, "onViewCreated: ");


    }



    }




