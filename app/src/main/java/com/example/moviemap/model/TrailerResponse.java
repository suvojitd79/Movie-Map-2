package com.example.moviemap.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrailerResponse {

    @SerializedName("results")
    private ArrayList<Trailer> trailers;

    public ArrayList<Trailer> getTrailers() {
        return trailers;
    }
}
