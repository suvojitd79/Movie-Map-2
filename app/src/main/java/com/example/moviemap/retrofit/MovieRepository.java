package com.example.moviemap.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.moviemap.MainActivity;
import com.example.moviemap.Movie_info;
import com.example.moviemap.model.Movie;
import com.example.moviemap.model.ResponseObject;
import com.example.moviemap.model.Review;
import com.example.moviemap.model.ReviewResponse;
import com.example.moviemap.model.Trailer;
import com.example.moviemap.model.TrailerResponse;
import com.example.moviemap.retrofit.MovieDb;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private Context context;

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MainActivity.BASE_MOVIEDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final MovieDb movieDb = retrofit.create(MovieDb.class);


    public MovieRepository(Context context) {

        this.context = context;
    }



    //get the movies
    public void getMovies(String filter_type, String api_key, String language, int page) {

        Call<ResponseObject> call = movieDb.getMovies(filter_type, api_key, language, page);


        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    MainActivity mainActivity = (MainActivity) context;
                    ArrayList<Movie> movies = response.body().getMovies();
                    if (movies != null) {
                        mainActivity.movieAdapter.update(response.body().getMovies());
                        mainActivity.movies = movies; //update cache
                    } else
                        Toast.makeText(mainActivity, "Something went wrong!", Toast.LENGTH_LONG).show();

                    Log.d(MainActivity.TAG, response.code() + "");
                }

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });


    }

    //get the trailers
    public void getTrailers(int id, String api_key, String language) {


        Call<TrailerResponse> call = movieDb.getTrailers(id, api_key, language);

        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {

                if(response.isSuccessful() && response.code()==200){


                 Movie_info movie_info = (Movie_info) context;
                 ArrayList<Trailer> trailers = response.body().getTrailers();
                 if(trailers!=null)
                     movie_info.trailers = trailers;
                     movie_info.trailerAdapter.update(trailers);
                }

            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

                Log.d(MainActivity.TAG, t.getMessage());

            }

        });


    }



    //get the reviews
    public void getReviews(int id, String api_key, String language) {

        Call<ReviewResponse> call = movieDb.getReviews(id, api_key, language);

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {

                if(response.isSuccessful() && response.code()==200){

                    Movie_info movie_info = (Movie_info) context;
                    ArrayList<Review> reviews = response.body().getReviews();
                    if(reviews!=null){

                        movie_info.reviews = reviews;
                        movie_info.reviewAdapter.update(reviews);

                    }
                }

            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });


    }


    //get the similar movies
    public void getSimilar(int id, String api_key, String language) {

        Call<ResponseObject> call = movieDb.getSimilar(id, api_key, language);

        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {

                if(response.isSuccessful() && response.code()==200){

                    Movie_info movie_info = (Movie_info) context;
                    ArrayList<Movie> movies = response.body().getMovies();
                    if(movies!=null){

                        movie_info.movies = movies;
                        movie_info.movieAdapter.update(movies);

                    }


                }

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });


    }



}
