package com.example.moviemap.retrofit;

import android.content.Context;

public class MovieViewModel{

    private MovieRepository movieRepository;


    public MovieViewModel(Context context) {

        movieRepository = new MovieRepository(context);
    }


    public void getMovies(String filter_type,String api_key,String language,int page){

        movieRepository.getMovies(filter_type, api_key, language, page);

    }

    public void getTrailers(int id,String api_key,String language){

        movieRepository.getTrailers(id,api_key,language);

    }


    public void getReviews(int id,String api_key,String language){

        movieRepository.getReviews(id,api_key,language);

    }


    public void getSimilar(int id,String api_key,String language){

        movieRepository.getSimilar(id,api_key,language);

    }

}
