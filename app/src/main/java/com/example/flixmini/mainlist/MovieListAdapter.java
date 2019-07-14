package com.example.flixmini.mainlist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixmini.R;
import com.example.flixmini.dto.MovieEntity;
import com.example.flixmini.utils.DateLocalizationService;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.flixmini.utils.Constants.IMAGES_URL;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    public static String TAG = MovieListAdapter.class.getCanonicalName();
    private List<MovieEntity> movieEntities;
    /**
     * TODO Dagger (!)
     */
    private DateLocalizationService mDateLocalizationService = new DateLocalizationService();

    public MovieListAdapter(List<MovieEntity> movieEntities) {
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
        String description = movie.getOverview();
        if (description.length() > 55) {
            description = String.format("%s...", description.substring(0, 50));
        }
        holder.mOverviewTextView.setText(description);

        String localizedDate = mDateLocalizationService.localizeDate(movie.getReleaseDate());
        holder.mReleaseDateTextView.setText(localizedDate);
        if (movie.getPosterPath() == null) {
            Log.d(TAG, "movie.getPosterPath()==null");
        } else {
            Picasso.get().load(IMAGES_URL + movie.getPosterPath()).into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return movieEntities.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTitleTextView;
        TextView mOverviewTextView;
        TextView mReleaseDateTextView;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTitleTextView = itemView.findViewById(R.id.titleTextView);
            mOverviewTextView = itemView.findViewById(R.id.overviewTextView);
            mReleaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
        }
    }
}
