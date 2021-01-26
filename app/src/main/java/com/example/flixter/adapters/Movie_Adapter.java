package com.example.flixter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.ViewHolder>{

    public final String TAG = getClass().getCanonicalName();
    Context context;
    List<Movie> movies;

    public Movie_Adapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, overview;
        private ImageView poster;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            overview = itemView.findViewById(R.id.overview);
            poster = itemView.findViewById(R.id.poster);
        }

        public void bind(Movie movie) {
            title.setText(movie.get_title());
            overview.setText(movie.get_overview());
            Glide.with(context).load(movie.get_poster_path()).into(poster);
        }
    }
}
