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

import com.example.volleydemo.API.ApiClient;
import com.example.volleydemo.API.ApiInterface;
import com.example.volleydemo.R;
import com.example.volleydemo.adapters.TopMoviesAdapter;
import com.example.volleydemo.customWidgets.CustomProgressDialog;
import com.example.volleydemo.model.MoviesResponse;
import com.example.volleydemo.model.TopMovie;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.volleydemo.extras.Constants.API_KEY;
import static com.example.volleydemo.extras.Constants.MOVIE_ARGS;

public class TopMoviesFragment extends Fragment implements TopMoviesAdapter.OnMoreInfoClickListener{

    public static final String TAG = TopMoviesFragment.class.getSimpleName();

    RecyclerView mRecyclerView;


    private TopMoviesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    private CustomProgressDialog progressDialog;


    private DialogFragmentTopMovies dialogFragmentTopMovies;
    TopMoviesAdapter.OnMoreInfoClickListener onMoreInfoClickListener;

   // private final static String API_KEY = "9da795673e6721fb2225506edd8d78f5";


    


    public TopMoviesFragment(){

    }

    private void initView(View view) {

        Log.d(TAG, "initView: TopMoviesFragment");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerviewBoxOffice);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        onMoreInfoClickListener=this;


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

        showProgressDialog(true,getResources().getString(R.string.progress_topmovies));
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
                mAdapter = new TopMoviesAdapter(getActivity(),R.layout.content_fragment_boxoffice,movies,onMoreInfoClickListener);
                mRecyclerView.setAdapter(mAdapter);


                showProgressDialog(false,"done");
                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                showProgressDialog(false,"failed to get data");
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


    private void showProgressDialog(boolean shouldShow, String message) {
        if (shouldShow) {
            if (progressDialog == null)

            progressDialog = new CustomProgressDialog(getContext(), message, R.style.ProgressDialogTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();

        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        }
    }

    @Override
    public void onMoreInfoClick(TopMovie topMovie) {

        //MyApplication.SelectedMovie = movie;
        if(topMovie!=null){
            Log.d(TAG, "onMoreInfoClick: movie is not null");
        }else{
            Log.d(TAG, "onMoreInfoClick: movie is null");
        }
        Bundle args = new Bundle();
        args.putString(MOVIE_ARGS, new Gson().toJson(topMovie));
        dialogFragmentTopMovies = new DialogFragmentTopMovies(getContext());
        dialogFragmentTopMovies.setArguments(args);
        dialogFragmentTopMovies.show(getActivity().getFragmentManager(),"TopMoviesFragment");





    }



    }




