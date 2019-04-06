package com.example.moviemap.architecture_component;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieFavDao {

    @Insert
    public void add(MovieFavEntity movieFavEntity);

    @Query("DELETE FROM fav_table WHERE movie_id=:movie_id")
    public abstract void remove(int movie_id);

    @Query("SELECT * FROM fav_table ORDER BY id DESC")
    public abstract LiveData<List<MovieFavEntity>> getAll();

    @Query("SELECT COUNT(*) FROM fav_table WHERE movie_id=:mov_id")
    public int isValid(int mov_id);

}
