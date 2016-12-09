package com.example.ajayrwarrier.cinemabox;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by Ajay R Warrier on 04-12-2016.
 */
public class Trailers {
    @SerializedName("name")
    private String trailerName;
    @SerializedName("key")
    private String trailerUrl;
    public String getTrailerUrl() {
        return trailerUrl;
    }
    public String getTrailerName() {
        return trailerName;
    }
    public static class TrailerResult {
        private List<Trailers> results;
        public List<Trailers> getResults() {
            return results;
        }
    }
}
