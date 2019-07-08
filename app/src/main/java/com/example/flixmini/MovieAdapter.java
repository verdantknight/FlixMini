package com.example.flixmini;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixmini.dto.MovieEntity;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieEntity> movieEntities;

    public MovieAdapter(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieEntity movie = movieEntities.get(position);

        holder.mTitleTextView.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieEntities.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mOverviewTextView;
        public TextView mReleaseDateTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTitleTextView = itemView.findViewById(R.id.titleTextView);
            mOverviewTextView = itemView.findViewById(R.id.overviewTextView);
            mReleaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
        }
    }
}
