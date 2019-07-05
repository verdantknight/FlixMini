package com.example.flixmini;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.flixmini.dto.MovieEntity;
import com.example.flixmini.dto.PageEntity;
import com.example.flixmini.network.RetrofitInterface;
import com.example.flixmini.utils.Constants;

import java.io.IOException;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends ListActivity {

    public static String TAG = MainActivity.class.getCanonicalName();
    /**
     * TODO DAGGER
     */
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Entering MainActivity.onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<PageEntity> call = retrofitInterface.getMovies(Constants.API_KEY, Constants.LANGUAGE);

        Log.d(TAG, "call.enqueue()");
        call.enqueue(new Callback<PageEntity>() {
            @Override
            public void onResponse(Call<PageEntity> call, Response<PageEntity> response) {
                LinkedList<String> movieNames = new LinkedList<String>();
                if (response.isSuccessful()) {
                    Log.d(TAG, "response.isSuccessful()");
                    for (MovieEntity movieEntity : response.body().getResults()) {
                        movieNames.add(movieEntity.getTitle());
                    }
                    setListAdapter(
                            new ArrayAdapter<String>(
                                    MainActivity.this,
                                    android.R.layout.simple_list_item_1,
                                    movieNames
                            )
                    );
                } else {
                    try {
                        Log.e(TAG, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<PageEntity> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

        Log.d(TAG, "Exiting MainActivity.onCreate()");
    }
}
