package com.example.moviemap;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.moviemap.adapter.FavAdapter;
import com.example.moviemap.architecture_component.FavViewModel;
import com.example.moviemap.architecture_component.MovieFavEntity;
import com.example.moviemap.databinding.ActivityFavouritesBinding;
import com.example.moviemap.model.Movie;
import com.google.gson.Gson;

import java.util.List;

public class Favourites extends AppCompatActivity implements OnClickMovie{

    private ActivityFavouritesBinding activityFavouritesBinding;
    private FavAdapter favAdapter;
    private FavViewModel favViewModel;
    public static List<MovieFavEntity> movieFavEntities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFavouritesBinding = DataBindingUtil.setContentView(this, R.layout.activity_favourites);
        setContentView(activityFavouritesBinding.getRoot());

        favAdapter = new FavAdapter(this);
        activityFavouritesBinding.recyclerFav.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        activityFavouritesBinding.recyclerFav.setAdapter(favAdapter);


        //set the toolbar
        setSupportActionBar(activityFavouritesBinding.toolBar);
        activityFavouritesBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Favourites.this, MainActivity.class));
                finish();

            }
        });

        //set the view model
        favViewModel = ViewModelProviders.of(this).get(FavViewModel.class);
        favViewModel.getListLiveData().observe(this, new Observer<List<MovieFavEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieFavEntity> movieFavEntities) {

                if (movieFavEntities != null) {


                    Favourites.movieFavEntities = movieFavEntities;
                    favAdapter.update(movieFavEntities);

                }

            }
        });


    }

    @Override
    public void click(int position) {


        Gson gson = new Gson();
        Intent intent = new Intent(this,Movie_info.class);
        MovieFavEntity mo = movieFavEntities.get(position);
        Movie movie = new Movie(mo.getVote_count(),mo.getMovie_id(),mo.getVote_average(),mo.getTitle(),mo.getPoster_path(),mo.getOriginal_language(),mo.getOriginal_title(),new int[3],mo.getOverview(),mo.getRelease_date());
        String m = gson.toJson(movie);
        intent.putExtra("movie",m);
        ViewModelProviders.of(this).get(FavViewModel.class)
                .isValid(movie.getId());
        startActivity(intent);
    }



}
