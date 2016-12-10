package com.example.ajayrwarrier.cinemabox;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
public class MainActivity extends AppCompatActivity {
    static List<MovieThumbnail> movies;
    static SharedPreferences preferences;
    private static Bundle mBundleRecyclerViewState;
    RestAdapter restAdapter;
    ApiService service;
    String KEY_RECYCLER_STATE;
    RecyclerView.LayoutManager layoutManager;
    Parcelable mListState;
    String API_KEY = "YOUR_API_KEY";
    private RecyclerView recyclerView;
    private MovieGridAdapter movieGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KEY_RECYCLER_STATE = getString(R.string.state);
        preferences = getSharedPreferences("IdPref", MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieGridAdapter = new MovieGridAdapter(this);
        layoutManager = recyclerView.getLayoutManager();
        recyclerView.setAdapter(movieGridAdapter);
        movies = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            movies.add(new MovieThumbnail());
        }
        movieGridAdapter.setMovieList(movies);
        restAdapter = new RestAdapter.Builder().setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", API_KEY);
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        Popular();
    }
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        // Save list state
        mListState = layoutManager.onSaveInstanceState();
        state.putParcelable(KEY_RECYCLER_STATE, mListState);
    }
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // Retrieve list state and list/item positions
        if (state != null)
            mListState = state.getParcelable(KEY_RECYCLER_STATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        List<MovieThumbnail> list = new ArrayList<>();
        if (mListState != null) {
            layoutManager.onRestoreInstanceState(mListState);
        }
        for (MovieThumbnail item : list) {
            list.add(item);
        }
        movieGridAdapter.setMovieList(list);
        recyclerView.setAdapter(movieGridAdapter);
    }
    // Creates the Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuSortPopular:
                Popular();
                return true;
            case R.id.menuSortRating:
                Rating();
                return true;
            case R.id.menuFavorite:
                Favorited();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Network call for PopularMovies
    private void Popular() {
        service = restAdapter.create(ApiService.class);
        service.getPopularMovies(new Callback<MovieThumbnail.MovieResult>() {
            @Override
            public void success(MovieThumbnail.MovieResult movieResult, Response response) {
                if (movieResult != null) {
                    movieGridAdapter.setMovieList(movieResult.getResults());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
    // Network call for RatedMovies
    private void Rating() {
        service = restAdapter.create(ApiService.class);
        service.getRatedMovies(new Callback<MovieThumbnail.MovieResult>() {
            @Override
            public void success(MovieThumbnail.MovieResult movieResult, Response response) {
                if (movieResult != null) {
                    movieGridAdapter.setMovieList(movieResult.getResults());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
    private void Favorited() {
        final List<MovieThumbnail> list = new ArrayList<>();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", API_KEY);
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        service = restAdapter.create(ApiService.class);
        Map<String, ?> keys = preferences.getAll();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            service.getFavoritedMovies(entry.getValue().toString(), new Callback<MovieThumbnail>() {
                @Override
                public void success(MovieThumbnail movieResult, Response response) {
                    if (movieResult != null) {
                        list.add(movieResult);
                        movieGridAdapter.setMovieList(list);
                    }
                }
                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }
    }
}

