package com.example.ajayrwarrier.cinemabox;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Ajay R Warrier on 02-12-2016.
 */
public class DetailActivity extends AppCompatActivity {
    int imageId = 1;
    @BindView(R.id.movieNameView)
    TextView movieName;
    @BindView(R.id.releasedateView)
    TextView releaseDate;
    @BindView(R.id.ratingView)
    TextView rating;
    @BindView(R.id.synopsisView)
    TextView synopsis;
    @BindView(R.id.posterView)
    ImageView posterView;
    @BindView(R.id.trailerButton)
    Button trailerButton;
    @BindView(R.id.reviewButton)
    Button reviewButton;
    @BindView(R.id.toggleButton)
    ToggleButton toggleButton;
    SharedPreferences preferences, idpreferences;
    List<MovieThumbnail> movie = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("MoviePref", MODE_PRIVATE);
        idpreferences = getSharedPreferences("IdPref", MODE_PRIVATE);
        final SharedPreferences.Editor edit = preferences.edit();
        final SharedPreferences.Editor idedit = idpreferences.edit();
        final String id = getIntent().getStringExtra("id");
        final String name = getIntent().getStringExtra("MovieName");
        String poster = getIntent().getStringExtra("Poster");
        final String release = getIntent().getStringExtra("Release Date");
        String rate = getIntent().getStringExtra("Rating") + getString(R.string.ratingscore);
        String descr = getIntent().getStringExtra("Synopsis");
        movieName.setText(name);
        releaseDate.setText(release);
        rating.setText(rate);
        synopsis.setText(descr);
        Picasso.with(this)
                .load(poster)
                .placeholder(R.drawable.image_placeholder)
                .into(posterView);
        trailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, TrailerActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ReviewActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        Boolean favorited = preferences.getBoolean(id, false);
        toggleButton.setChecked(favorited);
        if (favorited) {
            Starred();
        } else {
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.unstarred));
        }
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Storing Data using SharedPreferences
                    edit.putBoolean(id, true);
                    idedit.putString(id, id);
                    idedit.commit();
                    edit.commit();
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.starred));
                } else {
                    edit.putBoolean(id, false);
                    edit.commit();
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.unstarred));
                }
            }
        });
    }
    private void Starred() {
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.starred));
    }
}

