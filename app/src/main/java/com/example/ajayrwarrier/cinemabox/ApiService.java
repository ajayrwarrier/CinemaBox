package com.example.ajayrwarrier.cinemabox;
import retrofit.Callback;
import retrofit.http.GET;
/**
 * Created by Ajay R Warrier on 02-12-2016.
 */
public interface ApiService {
    @GET("/movie/popular")
    void getPopularMovies(Callback<MovieThumbnail.MovieResult> cb);
    @GET("/movie/top_rated")
    void getRatedMovies(Callback<MovieThumbnail.MovieResult> cb);
}
