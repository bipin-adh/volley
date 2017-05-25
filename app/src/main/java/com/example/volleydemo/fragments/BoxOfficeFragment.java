package com.example.volleydemo.fragments;


import android.app.Application;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.google.gson.Gson;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.volleydemo.adapters.BoxOfficeAdapter;
import com.example.volleydemo.model.Movie;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleydemo.MyApplication;
import com.example.volleydemo.R;
import com.example.volleydemo.networks.VolleySingleton;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import static com.example.volleydemo.extras.Keys.EndPointBoxOffice.*;


public class BoxOfficeFragment extends Fragment implements BoxOfficeAdapter.OnMoreInfoClickListener{

    public static final String TAG = BoxOfficeFragment.class.getSimpleName();

    public static final String URL_THEMOVIEDB = "https://api.themoviedb.org/3/movie/popular";
    public static final String url = "https://api.themoviedb.org/3/movie/popular?api_key=9da795673e6721fb2225506edd8d78f5";
    public static final String MOVIE_ARGS = "my_movie";

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    RecyclerView mRecyclerView;

    private BoxOfficeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    BoxOfficeAdapter.OnMoreInfoClickListener onMoreInfoClickListener;
    private DialogFragmentMoreInfo dialogFragmentMoreInfo;


    private ArrayList<Movie> listMovies = new ArrayList<>();


    public BoxOfficeFragment(){

    }

    private void initView(View view) {


        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerviewBoxOffice);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        onMoreInfoClickListener=this;


    }




    private void sendJsonRequest(){
        Log.d(TAG, "sendJsonRequest: entered");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl(10), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //Toast.makeText(getActivity(),"RESPONSE"+ response, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: inside onResponse"+response.toString());
                parseJSONResponse(response);
                Log.d(TAG, "onResponse: after parsing json response");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: error found");

                Toast.makeText(getActivity(),"ERROR"+ error.getMessage()+error.getCause(), Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onErrorResponse: after toasting error");
            }
        });
        requestQueue.add(request);
        Log.d(TAG, "sendJsonRequest: after requestqueue.add");
    }

    private static String convertDateString(String date) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM d");

        String convertedDate = null;
        try {
            convertedDate = formatter.format(f.parse(date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convertedDate;
    }
    private void parseJSONResponse(JSONObject response){

        Log.d(TAG, "parseJSONResponse: inside parseJSONResponse method");
        if(response==null||response.length()==0){
            return;
        }

        try {

                StringBuilder data = new StringBuilder();


                JSONArray arrayMovies = response.getJSONArray(KEY_RESULTS);

            for(int i=0;i<arrayMovies.length();i++){

                JSONObject movieJsonObj = arrayMovies.getJSONObject(i);

                Movie movie = new Movie();

                movie.setTitle(movieJsonObj.getString(KEY_TITLE));
                movie.setOverview(movieJsonObj.getString(KEY_OVERVIEW));
                movie.setRating(Float.parseFloat(movieJsonObj.getString(KEY_RATING)));

                String convertedDate = convertDateString(movieJsonObj.getString(KEY_RELEASEDATE));


                Log.d(TAG, "parseJSONResponse: date format" + movieJsonObj.getString(KEY_RELEASEDATE)+ convertedDate);
                movie.setReleaseDate(convertedDate);





                movie.setPosterUrl(movieJsonObj.getString(KEY_URLPOSTER));
                movie.setBackdropUrl(movieJsonObj.getString(KEY_BACKDROPIMAGE));

                listMovies.add(movie);

                mAdapter = new BoxOfficeAdapter(getActivity(),listMovies,onMoreInfoClickListener);
                mRecyclerView.setAdapter(mAdapter);

            }
            Log.d(TAG, "parseJSONResponse: data"+ data.toString());



        } catch (JSONException e) {

            e.printStackTrace();
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        sendJsonRequest();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_boxoffice, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


    }


    public static String getRequestUrl(int limit){

        return URL_THEMOVIEDB+"?api_key="+ MyApplication.API_KEY_THEMOVIEDB+"&limit="+limit;

    }




    @Override
    public void onMoreInfoClick(Movie movie) {

        //MyApplication.SelectedMovie = movie;
        if(movie!=null){
            Log.d(TAG, "onMoreInfoClick: movie is not null");
        }else{
            Log.d(TAG, "onMoreInfoClick: movie is null");
        }
        Bundle args = new Bundle();
        args.putString(MOVIE_ARGS, new Gson().toJson(movie));
        dialogFragmentMoreInfo = new DialogFragmentMoreInfo(getContext());
        dialogFragmentMoreInfo.setArguments(args);
        dialogFragmentMoreInfo.show(getActivity().getFragmentManager(),"BoxOfficeFragment");





    }
}
