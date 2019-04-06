package com.example.moviemap.architecture_component;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavViewModel extends AndroidViewModel {

    private FavRepository favRepository;
    private LiveData<List<MovieFavEntity>> listLiveData;


    public FavViewModel(@NonNull Application application) {
        super(application);
        favRepository = new FavRepository(application);
        listLiveData = favRepository.getListLiveData();
    }

    public LiveData<List<MovieFavEntity>> getListLiveData(){

        return listLiveData;
    }


    public void add(MovieFavEntity movieFavEntity){

        favRepository.add(movieFavEntity);

    }

    public void remove(int mov_id){

        favRepository.remove(mov_id);

    }


    public void isValid(int mov_id){

        favRepository.isValid(mov_id);

    }


}
