package com.example.moviemap.architecture_component;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "fav_table")
public class MovieFavEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int vote_count;

    private int movie_id;

    private float vote_average;

    private String title;

    private String poster_path;

    private String original_language;

    private String original_title;

    private String overview;

    private String release_date;

    public MovieFavEntity(int vote_count, int movie_id, float vote_average, String title, String poster_path, String original_language, String original_title, String overview, String release_date) {
        this.vote_count = vote_count;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
