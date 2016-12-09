package com.example.ajayrwarrier.cinemabox;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ajay R Warrier on 04-12-2016.
 */
public class TrailerAdapter extends ArrayAdapter<Trailers> {
    private List<Trailers> trailers = new ArrayList<Trailers>();
    public TrailerAdapter(Activity context, List<Trailers> trailerArrayList) {
        super(context, 0, trailerArrayList);
        this.trailers = trailerArrayList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        final Trailers currentTrailer = trailers.get(position);
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.trailer_item, parent, false);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + currentTrailer.getTrailerUrl())));
            }
        });
        TextView nameField = (TextView) itemView.findViewById(R.id.trailerName);
        nameField.setText(currentTrailer.getTrailerName());
        return itemView;
    }
    public void setTrailerList(List<Trailers> trailersList) {
        trailers.clear();
        if (trailersList != null) {
            this.trailers.addAll(trailersList);
        }
        notifyDataSetChanged();
    }
}
