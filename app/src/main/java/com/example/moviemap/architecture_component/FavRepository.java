package com.example.moviemap.architecture_component;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.moviemap.MainActivity;
import com.example.moviemap.Movie_info;

import java.util.List;

public class FavRepository {

    private Context context;
    private LiveData<List<MovieFavEntity>> listLiveData;
    private MovieFavDao movieFavDao;

    public FavRepository(Context context) {

        this.context = context;
        movieFavDao = MovieRoomDb.getMovieRoomDb(context).getDao();
        listLiveData = movieFavDao.getAll();
    }

    public LiveData<List<MovieFavEntity>> getListLiveData() {

        return listLiveData;
    }


    public void add(MovieFavEntity movieFavEntity) {

        new Add(movieFavDao).execute(movieFavEntity);

    }

    public void remove(int mov_id) {

        new Remove(movieFavDao).execute(mov_id);

    }

    public void isValid(int mov_id){

        new Get(movieFavDao).execute(mov_id);

    }

    private class Get extends AsyncTask<Integer,Void,Void>{

        private MovieFavDao movieFavDao;

        public Get(MovieFavDao movieFavDao) {
            this.movieFavDao = movieFavDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            int count = movieFavDao.isValid(integers[0]);
            if(count!=0) Movie_info.isFavourite = false;
            Log.d(MainActivity.TAG, count+"");
            return null;
        }
    }

    private class Add extends AsyncTask<MovieFavEntity, Void, Void> {

        private MovieFavDao movieFavDao;

        public Add(MovieFavDao movieFavDao) {
            this.movieFavDao = movieFavDao;
        }

        @Override
        protected Void doInBackground(MovieFavEntity... movieFavEntities) {
            this.movieFavDao.add(movieFavEntities[0]);
            return null;
        }
    }

    private class Remove extends AsyncTask<Integer, Void, Void> {

        private MovieFavDao movieFavDao;

        public Remove(MovieFavDao movieFavDao) {
            this.movieFavDao = movieFavDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
           this.movieFavDao.remove(integers[0]);
            return null;
        }
    }



}
