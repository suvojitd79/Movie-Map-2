package com.example.moviemap;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moviemap.adapter.MovieAdapter;
import com.example.moviemap.architecture_component.FavViewModel;
import com.example.moviemap.databinding.ActivityMainBinding;
import com.example.moviemap.model.Movie;
import com.example.moviemap.retrofit.MovieViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener,OnClickMovie
{

    //constant key for checking log messages
    public static final String TAG = "d99";
    private Menu menu;
    private Toolbar toolbar;
    private ActivityMainBinding activityMainBinding;
    private SharedPreferences sharedPreferences;
    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/original/";
    public static final String BASE_MOVIEDB_URL = "https://api.themoviedb.org/";
    private RecyclerView recyclerView;
    public static MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    public static volatile ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setContentView(activityMainBinding.getRoot());

        //set the toolbar
        setSupportActionBar((Toolbar) activityMainBinding.toolBar);

        //register a shared preference
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        String key = sharedPreferences.getString(getResources().getString(R.string.sort_key),getResources().getString(R.string.m_v_1));

        movieViewModel = new MovieViewModel(this);
        recyclerView = activityMainBinding.recyclerViewHomepage;




        //render the UI
        renderUI(key);

    }


    private void renderUI(String uri){

        int span;
        int width = (int) Resources.getSystem().getDisplayMetrics().widthPixels;

        if(width<=1080) span=2;
        else span=3;


        movieAdapter = new MovieAdapter(this,(int)width/span-20);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,span));

        String API_KEY = getResources().getString(R.string.api_key);
        String language = getResources().getString(R.string.language);
        int page = 1 ; //default
        movieViewModel.getMovies(uri,API_KEY,language,page);

    }



    private int getWidth(){

        int width = (int) Resources.getSystem().getDisplayMetrics().widthPixels;
        return width;

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.main_actionbar,menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.settings){

            Intent intent = new Intent(MainActivity.this,Preferences.class);
            startActivity(intent);

        }

        if(id==R.id.favourites){

            Intent intent = new Intent(MainActivity.this,Favourites.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        renderUI(sharedPreferences.getString(key,getResources().getString(R.string.m_v_1)));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void click(int position) {

        Gson gson = new Gson();
        Intent intent = new Intent(this,Movie_info.class);
        String movie = gson.toJson(movies.get(position));
        intent.putExtra("movie",movie);
        ViewModelProviders.of(this).get(FavViewModel.class)
                .isValid(movies.get(position).getId());
        startActivity(intent);
    }


}
