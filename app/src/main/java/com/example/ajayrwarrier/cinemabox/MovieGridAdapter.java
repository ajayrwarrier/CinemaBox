package com.example.ajayrwarrier.cinemabox;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ajay R Warrier on 01-12-2016.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieViewHolder> {
    private List<MovieThumbnail> mMovieList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    public MovieGridAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMovieList = new ArrayList<>();
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_thumbnail_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieThumbnail movie = mMovieList.get(position);
        holder.movieName = movie.getTitle();
        holder.synopsis = movie.getDescript();
        holder.poster = movie.getPoster();
        holder.releaseDate = movie.getReleaseDate();
        holder.rating = movie.getRating();
        holder.id = movie.getId();
        Picasso.with(mContext)
                .load(movie.getPoster())
                .placeholder(R.drawable.image_placeholder)
                .into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }
    public void setMovieList(List<MovieThumbnail> movieList) {
        this.mMovieList.clear();
        if (movieList != null) {
            this.mMovieList.addAll(movieList);
        }
        notifyDataSetChanged();
    }
    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        String movieName, poster, releaseDate, rating, synopsis, id;
        public MovieViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            imageView = (ImageView) itemView.findViewById(R.id.gridImageView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("MovieName", movieName);
            intent.putExtra("Poster", poster);
            intent.putExtra("Release Date", releaseDate);
            intent.putExtra("Rating", rating);
            intent.putExtra("Synopsis", synopsis);
            v.getContext().startActivity(intent);
        }
    }
}