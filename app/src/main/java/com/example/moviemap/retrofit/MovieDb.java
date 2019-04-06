package com.example.moviemap.retrofit;

import com.example.moviemap.model.ResponseObject;
import com.example.moviemap.model.ReviewResponse;
import com.example.moviemap.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDb {


    @GET("3/movie/{filter_type}")
    Call<ResponseObject> getMovies(@Path("filter_type") String filter_type, @Query("api_key") String api_key,@Query("language") String language,@Query("page") int page);

    @GET("3/movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(@Path("movie_id") int movie_id,@Query("api_key") String api_key,@Query("language") String language);

    @GET("3/movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(@Path("movie_id") int movie_id, @Query("api_key") String api_key, @Query("language") String language);

    @GET("3/movie/{movie_id}/similar")
    Call<ResponseObject> getSimilar(@Path("movie_id") int movie_id,@Query("api_key") String api_key,@Query("language") String language);


}
