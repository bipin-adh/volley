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
import com.example.volleydemo.model.Movie;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.MyHolder> {


    public static final String TAG = BoxOfficeAdapter.class.getSimpleName();

    Context context ;
    List<Movie> Movielist;

    private ImageLoader imageLoader;
    private DisplayImageOptions options;


    private OnMoreInfoClickListener onMoreInfoClickListener;

    public BoxOfficeAdapter(Context context, List<Movie> Movielist,OnMoreInfoClickListener onMoreInfoClickListener){

        Log.d(TAG, "BoxOfficeAdapter: size  movielist"+ Movielist.size());
        this.context = context;
        this.Movielist= Movielist;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_fragment_boxoffice,
                parent, false);
        return new BoxOfficeAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {

        final Movie movie = Movielist.get(position);

        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewReleaseDate.setText(movie.getReleaseDate().toString());
        holder.cardViewRatingBar.setRating(movie.getRating()/2);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onMoreInfoClickListener.onMoreInfoClick(movie);
            }
        });


        imageLoader.displayImage(movie.getPosterUrl(), holder.poster_image, options, new ImageLoadingListener() {
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

    public interface OnMoreInfoClickListener {

        void onMoreInfoClick(Movie movie);
    }

}
