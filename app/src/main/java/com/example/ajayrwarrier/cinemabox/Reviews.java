package com.example.ajayrwarrier.cinemabox;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by Ajay R Warrier on 06-12-2016.
 */
public class Reviews {
    @SerializedName("content")
    String content;
    @SerializedName("author")
    String author;
    public String getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }
    public static class ReviewResult {
        private List<Reviews> results;
        public List<Reviews> getResults() {
            return results;
        }
    }
}
