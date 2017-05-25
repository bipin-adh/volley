package com.example.volleydemo.model;

import com.example.volleydemo.MyApplication;

import java.util.Date;


public class Movie {

    private String id;
    private String title;
    private String overview;
    private String releaseDate;
    private String genreId;
    private String posterUrl;
    private float rating;
    private String backdropUrl;

    public void setBackdropUrl(String backdropUrl) {
        this.backdropUrl = MyApplication.backdrop_image_location+backdropUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setPosterUrl(String posterUrl)
    {
        this.posterUrl = MyApplication.image_Location_url+ posterUrl;
    }

    public String getPosterUrl()
    {
        return posterUrl;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreId() {
        return genreId;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
