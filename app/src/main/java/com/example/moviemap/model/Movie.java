package com.example.moviemap.model;

public class Movie {

    private int vote_count;

    private int id;

    private float vote_average;

    private String title;

    private String poster_path;

    private String original_language;

    private String original_title;

    private int[] genre_ids;

    private String overview;

    private String release_date;


    public Movie(int vote_count, int id, float vote_average, String title, String poster_path, String original_language, String original_title, int[] genre_ids, String overview, String release_date) {
        this.vote_count = vote_count;
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.overview = overview;
        this.release_date = release_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getId() {
        return id;
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

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}
