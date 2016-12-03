package com.example.ajayrwarrier.cinemabox;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
/**
 * Created by Ajay R Warrier on 02-12-2016.
 */
public class DetailActivity extends AppCompatActivity {
    List<MovieThumbnail> mMovieList;
    TextView movieName;
    TextView releaseDate;
    TextView rating;
    TextView synopsis;
    ImageView posterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String name = getIntent().getStringExtra("MovieName");
        String poster = getIntent().getStringExtra("Poster");
        String release = getIntent().getStringExtra("Release Date");
        String rate = getIntent().getStringExtra("Rating") + getString(R.string.ratingscore);
        String descr = getIntent().getStringExtra("Synopsis");

        movieName = (TextView) findViewById(R.id.movieNameView);
        releaseDate = (TextView) findViewById(R.id.releasedateView);
        rating = (TextView) findViewById(R.id.ratingView);
        synopsis = (TextView) findViewById(R.id.synopsisView);
        posterView = (ImageView) findViewById(R.id.posterView);

        movieName.setText(name);
        releaseDate.setText(release);
        rating.setText(rate);
        synopsis.setText(descr);
        Picasso.with(this)
                .load(poster)
                .placeholder(R.drawable.image_placeholder)
                .into(posterView);
    }
}
