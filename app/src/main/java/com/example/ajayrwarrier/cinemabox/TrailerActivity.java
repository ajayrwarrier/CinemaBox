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
public class TrailerActivity extends AppCompatActivity {
    RestAdapter restAdapter;
    List<Trailers> trailersList = new ArrayList<>();
    private TrailerAdapter adapter;
    @BindView(R.id.trailer_list)
    ListView trailerListView;
    String API_KEY = "YOUR_API_KEY";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        String id = getIntent().getStringExtra("id");
        ButterKnife.bind(this);
        adapter = new TrailerAdapter(this, trailersList);
        trailerListView.setAdapter(adapter);
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
        service.getTrailerVidoes(new Callback<Trailers.TrailerResult>() {
            @Override
            public void success(Trailers.TrailerResult trailerResult, Response response) {
                if (trailerResult != null) {
                    adapter.setTrailerList(trailerResult.getResults());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
}
