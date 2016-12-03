package com.example.ajayrwarrier.cinemabox;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Created by Ajay R Warrier on 01-12-2016.
 */
public class MovieThumbnail {
    public static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500";
    @SerializedName("original_title")
    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String descript;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private String rating;

    public String getDescript() {
        return descript;
    }
    public String getPoster() {
        return IMAGE_PATH + poster;
    }
    public String getTitle() {
        return title;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getRating() {
        return rating;
    }
    public static class MovieResult {
        private List<MovieThumbnail> results;
        public List<MovieThumbnail> getResults() {
            return results;
        }
    }
}
