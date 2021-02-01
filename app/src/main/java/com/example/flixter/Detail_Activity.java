package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class Detail_Activity extends YouTubeBaseActivity {

    public final String TAG = getClass().getCanonicalName();
    private static final String YOUTUBE_API_KEY = "AIzaSyAtVoGZdhNul4XAvXng4gjta61Mzopu3M8",
    VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    private TextView on_screen_title, on_screen_overview;
    private RatingBar on_screen_rating;
    private YouTubePlayerView on_screen_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        on_screen_title = findViewById(R.id.movie_title);
        on_screen_overview = findViewById(R.id.overview);
        on_screen_rating = findViewById(R.id.rating_bar);
        on_screen_player = findViewById(R.id.player);

        Movie my_movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        on_screen_title.setText(my_movie.get_title());
        on_screen_overview.setText(my_movie.get_overview());
        on_screen_rating.setRating((float)my_movie.get_rating());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, my_movie.get_id()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {

                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");

                    if (results.length() == 0)
                        return;

                    String youtube_key = results.getJSONObject(0).getString("key");
                    initialize_the_tube(youtube_key);

                } catch (JSONException e) {
                    Log.e(TAG, "Failed to parse JSON", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });
    }

    private void initialize_the_tube(final String youtube_key) {

        on_screen_player.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "Success!");
                youTubePlayer.cueVideo(youtube_key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "Failure...");
            }
        });
    }
}