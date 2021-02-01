package com.example.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    private String poster_path;
    private String title;
    private String overview;
    private double rating;
    private int ID;

    // empty constructor needed by the Parceler library
    public Movie() {}

    public Movie(JSONObject jsonObject) throws JSONException {
        poster_path = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        ID = jsonObject.getInt("id");
    }

    public static List<Movie> from_json_array(JSONArray movie_json_array) throws JSONException {

        List<Movie> movies = new ArrayList<>();
        int len = movie_json_array.length();

        for (int i = 0; i < len; ++ i) {
            movies.add(new Movie(movie_json_array.getJSONObject(i)));
        }

        return movies;
    }

    public String get_poster_path() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", poster_path);
    }

    public String get_title() {
        return title;
    }

    public String get_overview() {
        return overview;
    }

    public double get_rating() {
        return rating;
    }

    public int get_id() {
        return ID;
    }
}
