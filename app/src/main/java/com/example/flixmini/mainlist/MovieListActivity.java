package com.example.flixmini.mainlist;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixmini.R;
import com.example.flixmini.dto.MovieEntity;
import com.example.flixmini.dto.PageEntity;

import java.util.ArrayList;


public class MovieListActivity extends AppCompatActivity implements MovieListContract.View {

    public static String TAG = MovieListActivity.class.getCanonicalName();
    /**
     * TODO DAGGER
     */
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieListPresenter mMovieListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Entering MovieListActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieAdapter(new ArrayList<MovieEntity>());
        mRecyclerView.setAdapter(mAdapter);
        mMovieListPresenter = new MovieListPresenter(this);
        mMovieListPresenter.loadMovieList();
        Log.d(TAG, "Exiting MovieListActivity.onCreate()");
    }

    @Override
    public void showPage(PageEntity page) {
        mAdapter = new MovieAdapter(page.getResults());
        mRecyclerView.setAdapter(mAdapter);
    }
}
