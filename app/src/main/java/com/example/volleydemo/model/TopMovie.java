package com.example.volleydemo.model;


import com.example.volleydemo.MyApplication;
import com.google.gson.annotations.SerializedName;

public class TopMovie {

    @SerializedName("id")
    private String id;
    @SerializedName("original_title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("genre_ids")
    private String genreId;
    @SerializedName("poster_path")
    private String posterUrl;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("backdrop_path")
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
