package com.example.volleydemo.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("results")
    private List<TopMovie> results;

    public void setResults(List<TopMovie> results) {
        this.results = results;
    }

    public List<TopMovie> getResults() {
        return results;
    }
}
