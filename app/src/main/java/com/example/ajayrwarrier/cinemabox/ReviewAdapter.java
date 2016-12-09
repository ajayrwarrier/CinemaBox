package com.example.ajayrwarrier.cinemabox;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ajay R Warrier on 06-12-2016.
 */
public class ReviewAdapter extends ArrayAdapter<Reviews> {
    private List<Reviews> reviews = new ArrayList<Reviews>();
    public ReviewAdapter(Activity context, List<Reviews> reviewArrayList) {
        super(context, 0, reviewArrayList);
        this.reviews = reviewArrayList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        final Reviews currentReview = reviews.get(position);
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.review_item, parent, false);
        }
        TextView contentField = (TextView) itemView.findViewById(R.id.contentView);
        contentField.setText(currentReview.getContent());
        TextView authorField = (TextView) itemView.findViewById(R.id.authorView);
        authorField.setText(currentReview.getAuthor());
        return itemView;
    }
    public void setReviewList(List<Reviews> reviewsList) {
        reviews.clear();
        if (reviewsList != null) {
            this.reviews.addAll(reviewsList);
        }
        notifyDataSetChanged();
    }
}
