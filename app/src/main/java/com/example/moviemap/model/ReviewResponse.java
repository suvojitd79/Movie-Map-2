package com.example.moviemap.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewResponse {

    @SerializedName("results")
    private ArrayList<Review> reviews;

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
