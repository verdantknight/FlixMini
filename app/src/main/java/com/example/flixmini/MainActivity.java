package com.example.flixmini;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixmini.dto.MovieEntity;
import com.example.flixmini.dto.PageEntity;
import com.example.flixmini.network.RetrofitInterface;
import com.example.flixmini.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getCanonicalName();
    /**
     * TODO DAGGER
     */
    private RetrofitInterface mRetrofitInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Entering MainActivity.onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieAdapter(new ArrayList<MovieEntity>());
        mRecyclerView.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<PageEntity> call = mRetrofitInterface.getMovies(Constants.API_KEY, Constants.LANGUAGE);

        Log.d(TAG, "call.enqueue()");
        call.enqueue(new Callback<PageEntity>() {
            @Override
            public void onResponse(@NonNull Call<PageEntity> call, @NonNull Response<PageEntity> response) {
                if (response.isSuccessful()) {
                    mAdapter = new MovieAdapter(response.body().getResults());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Log.d(TAG, "! NOT response.isSuccessful()");
                    try {
                        Log.e(TAG, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PageEntity> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

        Log.d(TAG, "Exiting MainActivity.onCreate()");
    }
}
