package com.example.flixmini.mainlist;

import com.example.flixmini.dto.PageEntity;
import com.example.flixmini.network.RetrofitInterface;
import com.example.flixmini.utils.Constants;
import com.example.flixmini.utils.Logger;
import com.example.flixmini.utils.SimpleLogger;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListPresenter {
    public static String TAG = MovieListPresenter.class.getCanonicalName();
    /**
     * TODO DAGGER
     */
    private Logger mLog = new SimpleLogger();
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

    public void loadMovieList() {
        mLog.d(TAG, "Entering loadMovieList()");
        Call<PageEntity> call = mRetrofitInterface.getMovies(Constants.API_KEY, Constants.LANGUAGE);

        mLog.d(TAG, "call.enqueue()");
        call.enqueue(new Callback<PageEntity>() {
            @Override
            public void onResponse(Call<PageEntity> call, Response<PageEntity> response) {
                if (response.isSuccessful()) {
                    mView.showPage(response.body());
                } else {
                    mLog.d(TAG, "! NOT response.isSuccessful()");
                    try {
                        mLog.e(TAG, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PageEntity> call, Throwable t) {
                mLog.e(TAG, t.getMessage());
            }
        });
        mLog.d(TAG, "Exiting loadMovieList()");
    }

    public void search(String query) {
        if (query.isEmpty()) {
            loadMovieList();
        } else {
            mLog.d(TAG, "Entering search()");
            mLog.d(TAG, String.format("query = %s", query));
            Call<PageEntity> call = mRetrofitInterface.searchMovies(Constants.API_KEY, Constants.LANGUAGE, query);

            mLog.d(TAG, "call.enqueue()");
            call.enqueue(new Callback<PageEntity>() {
                @Override
                public void onResponse(Call<PageEntity> call, Response<PageEntity> response) {
                    if (response.isSuccessful()) {
                        mView.showPage(response.body());
                    } else {
                        mLog.d(TAG, "! NOT search response.isSuccessful()");
                        try {
                            mLog.e(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PageEntity> call, Throwable t) {
                    mLog.e(TAG, t.getMessage());
                }
            });
            mLog.d(TAG, "Exiting search()");
        }
    }
}
