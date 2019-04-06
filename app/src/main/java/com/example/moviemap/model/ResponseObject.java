package com.example.moviemap.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseObject {

    @SerializedName("results")
    ArrayList<Movie> movies;


    public ArrayList<Movie> getMovies() {
        return movies;
    }


}
