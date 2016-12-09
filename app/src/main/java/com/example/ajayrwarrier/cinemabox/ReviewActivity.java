package com.example.ajayrwarrier.cinemabox;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Created by Ajay R Warrier on 06-12-2016.
 */
public class ReviewActivity extends AppCompatActivity {
    RestAdapter restAdapter;
    List<Reviews> reviewsList = new ArrayList<>();
    private ReviewAdapter adapter;
    @BindView(R.id.review_list)
    ListView reviewListView;
    String API_KEY = "YOUR_API_KEY";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        String id = getIntent().getStringExtra("id");
        ButterKnife.bind(this);
        adapter = new ReviewAdapter(this, reviewsList);
        reviewListView.setAdapter(adapter);
        restAdapter = new RestAdapter.Builder().setEndpoint("http://api.themoviedb.org/3/movie/" + id)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", API_KEY);
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        ApiService service = restAdapter.create(ApiService.class);
        service.getReviews(new Callback<Reviews.ReviewResult>() {
            @Override
            public void success(Reviews.ReviewResult reviewResult, Response response) {
                if (reviewResult != null) {
                    adapter.setReviewList(reviewResult.getResults());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
}
