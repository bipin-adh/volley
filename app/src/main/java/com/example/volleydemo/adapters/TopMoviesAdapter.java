package com.example.volleydemo.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.volleydemo.R;
import com.example.volleydemo.model.Movie;
import com.example.volleydemo.model.TopMovie;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.MyHolder>{

    public static final String TAG = TopMoviesAdapter.class.getSimpleName();

    Context context ;
    List<TopMovie> Movielist;

    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    private int rowLayout;



    public TopMoviesAdapter(Context context, int rowLayout,List<TopMovie> Movielist){


        this.context = context;
        this.Movielist= Movielist;
        this.rowLayout = rowLayout;

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
    public void onBindViewHolder(MyHolder holder, int position) {

        final TopMovie topMovie = Movielist.get(position);


        holder.textViewTitle.setText(topMovie.getTitle());
        holder.textViewReleaseDate.setText(topMovie.getReleaseDate().toString());
        holder.cardViewRatingBar.setRating(topMovie.getRating()/2);



    }

    @Override
    public int getItemCount() {
        return Movielist.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle , textViewReleaseDate;
        CircleImageView poster_image;
        RatingBar cardViewRatingBar;

       // CardView cardView;

        public MyHolder(View view) {
            super(view);
            textViewTitle = (TextView)view.findViewById(R.id.textViewTitle);
            textViewReleaseDate=(TextView)view.findViewById(R.id.textViewReleaseDate);
            poster_image = (CircleImageView)view.findViewById(R.id.poster_image);
            cardViewRatingBar=(RatingBar)view.findViewById(R.id.cardViewRatingBar);

            //cardView=(CardView)view.findViewById(R.id.cardViewList);
        }
    }


}
