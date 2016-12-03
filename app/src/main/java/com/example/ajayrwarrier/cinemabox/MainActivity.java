package com.example.ajayrwarrier.cinemabox;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    static List<MovieThumbnail> movies;
    private MovieGridAdapter movieGridAdapter;
    RestAdapter restAdapter;
    ApiService service;
    String API_KEY = "YOUR_API_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieGridAdapter = new MovieGridAdapter(this);
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
}
