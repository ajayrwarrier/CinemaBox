package com.example.ajayrwarrier.cinemabox;
import android.telecom.Call;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
/**
 * Created by Ajay R Warrier on 02-12-2016.
 */
public interface ApiService {
    @GET("/movie/popular")
    void getPopularMovies(Callback<MovieThumbnail.MovieResult> cb);
    @GET("/movie/top_rated")
    void getRatedMovies(Callback<MovieThumbnail.MovieResult> cb);
    @GET("/videos")
    void getTrailerVidoes(Callback<Trailers.TrailerResult> cb);
    @GET("/reviews")
    void getReviews(Callback<Reviews.ReviewResult> cb);
    @GET("/movie/{id}")
    void getFavoritedMovies(@Path("id") String id,Callback<MovieThumbnail> cb);
}
