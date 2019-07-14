package com.example.flixmini.mainlist;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.flixmini.dto.PageEntity;
import com.example.flixmini.network.RetrofitInterface;
import com.example.flixmini.utils.Constants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListPresenter {
    // TODO remove android logging from presenter
    public static String TAG = MovieListPresenter.class.getCanonicalName();
    /**
     * TODO DAGGER
     */
    private RetrofitInterface mRetrofitInterface;
    private MovieListContract.View mView;

    public MovieListPresenter(MovieListContract.View view) {
        mView = view;
        // TODO move
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    public void loadMovieList(){
        Log.d(TAG, "Entering loadMovieList()");
        Call<PageEntity> call = mRetrofitInterface.getMovies(Constants.API_KEY, Constants.LANGUAGE);

        Log.d(TAG, "call.enqueue()");
        call.enqueue(new Callback<PageEntity>() {
            @Override
            public void onResponse(@NonNull Call<PageEntity> call, @NonNull Response<PageEntity> response) {
                if (response.isSuccessful()) {
                    mView.showPage(response.body());
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
        Log.d(TAG, "Exiting loadMovieList()");
    }
}
