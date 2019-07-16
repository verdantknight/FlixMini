package com.example.flixmini.mainlist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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
    private EditText mSearchEditText;
    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Entering MovieListActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mSearchEditText = findViewById(R.id.searchEditText);
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                Log.d(TAG, "onEditorAction()");
                Log.d(TAG, String.format("String.valueOf(keyEvent) = %s", String.valueOf(keyEvent)));
                Log.d(TAG, String.format("String.valueOf(actionId) = %s", String.valueOf(actionId)));
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mMovieListPresenter.search(mSearchEditText.getText().toString());
                    mInputMethodManager.hideSoftInputFromWindow(mSearchEditText.getApplicationWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieListAdapter(new ArrayList<MovieEntity>());
        mRecyclerView.setAdapter(mAdapter);
        mMovieListPresenter = new MovieListPresenter(this);
        mMovieListPresenter.loadMovieList();
        Log.d(TAG, "Exiting MovieListActivity.onCreate()");
    }

    @Override
    public void showPage(PageEntity page) {
        mAdapter = new MovieListAdapter(page.getResults());
        mRecyclerView.setAdapter(mAdapter);
    }
}
