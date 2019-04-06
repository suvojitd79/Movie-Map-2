package com.example.moviemap;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.moviemap.adapter.RecommendationAdapter;
import com.example.moviemap.adapter.ReviewAdapter;
import com.example.moviemap.adapter.TrailerAdapter;
import com.example.moviemap.architecture_component.FavViewModel;
import com.example.moviemap.architecture_component.MovieFavEntity;
import com.example.moviemap.databinding.ActivityMovieInfoBinding;
import com.example.moviemap.model.Movie;
import com.example.moviemap.model.Review;
import com.example.moviemap.model.Trailer;
import com.example.moviemap.retrofit.MovieViewModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie_info extends AppCompatActivity implements View.OnClickListener, OnClickTrailer, OnClickMovie {

    private Movie movie;
    private ActivityMovieInfoBinding movieInfoBinding;
    private String shortString;
    private boolean shortStringEnabled = true;
    public static volatile boolean isFavourite = true;
    public static ArrayList<Trailer> trailers;//<---cache trailer pointer
    public static ArrayList<Review> reviews; //<----cache review pointer
    public static ArrayList<Movie> movies; //<--- cache movie pointer
    public static TrailerAdapter trailerAdapter;
    public static ReviewAdapter reviewAdapter;
    public static RecommendationAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private FavViewModel favViewModel;
    public static volatile List<MovieFavEntity> movieFavEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_info);
        setContentView(movieInfoBinding.getRoot());
        movieInfoBinding.readMore.setOnClickListener(this);
        movieInfoBinding.myFav.setOnClickListener(this);

        movieFavEntities = new ArrayList<>();

        //recyclerview for trailers
        trailerAdapter = new TrailerAdapter(this);
        movieInfoBinding.movieTrailers.setAdapter(trailerAdapter);
        movieInfoBinding.movieTrailers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //recyclerview for reviews
        reviewAdapter = new ReviewAdapter(this);
        movieInfoBinding.movieReviews.setAdapter(reviewAdapter);
        movieInfoBinding.movieReviews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //recyclerview for similar movies
        int span;
        int width = (int) Resources.getSystem().getDisplayMetrics().widthPixels;

        if (width <= 1080) span = 2;
        else span = 3;


        movieAdapter = new RecommendationAdapter(this, (int) width / span + 70);
        movieInfoBinding.movieRecommendations.setAdapter(movieAdapter);
        movieInfoBinding.movieRecommendations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        movieViewModel = new MovieViewModel(this);


        favViewModel = ViewModelProviders.of(this).get(FavViewModel.class);


    }

    @Override
    protected void onResume() {
        super.onResume();

        Gson gson = new Gson();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {


            movie = gson.fromJson(bundle.getString("movie"), Movie.class);

            //  Log.d(MainActivity.TAG, movie.getId()+" "+getResources().getString(R.string.api_key));


            //call for trailers
            movieViewModel.getTrailers(movie.getId(), getResources().getString(R.string.api_key), getResources().getString(R.string.language));


            //call for reviews
            movieViewModel.getReviews(movie.getId(), getResources().getString(R.string.api_key), getResources().getString(R.string.language));


            //call for recommendation
            movieViewModel.getSimilar(movie.getId(), getResources().getString(R.string.api_key), getResources().getString(R.string.language));


            movieInfoBinding.movieLikes.setText(movie.getVote_count() + "");
            movieInfoBinding.movieRatings.setText(movie.getVote_average() + "");
            shortString = getShortDescription(movie.getOverview());
            if (shortString.equals(movie.getOverview()))
                movieInfoBinding.readMore.setVisibility(View.GONE);
            movieInfoBinding.movieDes.setText(shortString);
            movieInfoBinding.movieTitle.setText(movie.getTitle());
            Picasso.get().load(MainActivity.BASE_IMAGE_URL + movie.getPoster_path())
                    .into(movieInfoBinding.movieImg);


            if (!isFavourite) movieInfoBinding.myFav.setImageResource(R.drawable.love);
            else movieInfoBinding.myFav.setImageResource(R.drawable.ic_favorite_black_24dp);

        } else {

            Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.read_more) {
            if (shortStringEnabled) {
                movieInfoBinding.movieDes.setText(movie.getOverview());
                movieInfoBinding.readMore.setText("Show less");
            } else {
                movieInfoBinding.movieDes.setText(shortString);
                movieInfoBinding.readMore.setText("Read more");
            }

            shortStringEnabled = !shortStringEnabled;
        } else if (id == R.id.my_fav) {

            if (isFavourite) {
                //add
                favViewModel.add(new MovieFavEntity(movie.getVote_count(), movie.getId(), movie.getVote_average(), movie.getTitle(), movie.getPoster_path(), movie.getOriginal_language(), movie.getOriginal_title(), movie.getOverview(), movie.getRelease_date()));
                movieInfoBinding.myFav.setImageResource(R.drawable.love);
                Toast.makeText(this, "Marked as Favourite", Toast.LENGTH_SHORT).show();


            } else {
                //remove
                favViewModel.remove(movie.getId());
                movieInfoBinding.myFav.setImageResource(R.drawable.ic_favorite_black_24dp);
                Toast.makeText(this, "Removed from Favourites", Toast.LENGTH_SHORT).show();
            }
            isFavourite = !isFavourite;
        }
    }

    private String getShortDescription(String des) {

        Matcher matcher = Pattern.compile(".*?\\.").matcher(des);
        if (matcher.find()) {
            return matcher.group(0);
        } else
            return "";
    }

    @Override
    public void clickTrailer(int position) {

        // Toast.makeText(this,trailers.get(position).getKey()+"",Toast.LENGTH_LONG).show();

        Intent youtube = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailers.get(position).getKey()));
        Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailers.get(position).getKey()));

        try {

            try {

                startActivity(youtube);

            } catch (ActivityNotFoundException e) {

                startActivity(web);

            }
        } catch (Exception e) {

            Toast.makeText(this, "You don't have proper application installed in your phone to play this video", Toast.LENGTH_LONG).show();

        }


    }


    @Override
    public void click(int position) {


        Gson gson = new Gson();
        Intent intent = new Intent(Movie_info.this, Movie_info.class);
        String movie = gson.toJson(movies.get(position));
        intent.putExtra("movie", movie);
        startActivity(intent);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //reset the value
        isFavourite = true;
        finish();
    }
}
