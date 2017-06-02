package com.example.volleydemo.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.volleydemo.R;
import com.example.volleydemo.model.TopMovie;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.MyHolder>{

    public static final String TAG = TopMoviesAdapter.class.getSimpleName();

    Context context ;
    List<TopMovie> Movielist;

    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    private int rowLayout;
    private String date,convertedDate,movieTitle , moviePosterUrl;
    private float movieRating;

    private OnMoreInfoClickListener onMoreInfoClickListener;

    public TopMoviesAdapter(Context context, int rowLayout,List<TopMovie> Movielist,OnMoreInfoClickListener onMoreInfoClickListener){


        this.context = context;
        this.Movielist= Movielist;
        this.rowLayout = rowLayout;
        this.onMoreInfoClickListener = onMoreInfoClickListener;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(null) // resource or drawable
                .showImageForEmptyUri(null) // resource or drawable
                .showImageOnFail(null)
                .cacheOnDisk(true).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,
                parent, false);
        return new TopMoviesAdapter.MyHolder(view);

    }

    @Override
    public void onBindViewHolder( final MyHolder holder, int position) {

        final TopMovie topMovie = Movielist.get(position);

        date = topMovie.getReleaseDate().toString();
        convertedDate = convertDateString(date);
        movieTitle = topMovie.getTitle();
        movieRating = topMovie.getRating()/2;
        moviePosterUrl = topMovie.getPosterUrl();



        holder.textViewTitle.setText(movieTitle);
        holder.textViewReleaseDate.setText(convertedDate);
        holder.cardViewRatingBar.setRating(movieRating);

        Log.d(TAG, "onBindViewHolder: top movies poster url " + topMovie.getPosterUrl());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoreInfoClickListener.onMoreInfoClick(topMovie);
            }
        });

        imageLoader.displayImage(moviePosterUrl, holder.poster_image, options,new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                holder.poster_image.setImageResource(R.drawable.default_poster);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {


            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                holder.poster_image.setImageResource(R.drawable.default_poster);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Movielist.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle , textViewReleaseDate;
        CircleImageView poster_image;
        RatingBar cardViewRatingBar;

        CardView cardView;

        public MyHolder(View view) {
            super(view);
            textViewTitle = (TextView)view.findViewById(R.id.textViewTitle);
            textViewReleaseDate=(TextView)view.findViewById(R.id.textViewReleaseDate);
            poster_image = (CircleImageView)view.findViewById(R.id.poster_image);
            cardViewRatingBar=(RatingBar)view.findViewById(R.id.cardViewRatingBar);

            cardView=(CardView)view.findViewById(R.id.cardViewList);
        }
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

    public interface OnMoreInfoClickListener {

        void onMoreInfoClick(TopMovie topMovie);
    }



}
