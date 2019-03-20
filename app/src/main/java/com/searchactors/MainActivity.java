package com.searchactors;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    protected List<Actor> popularActors = new ArrayList<>() ;
    protected int queryPage = 1 ;
    protected int totalPage ;

    private static final String apiKey = "90f9f56e299e21f06338b0197a5ff6f6" ;
    // TODO
    // private boolean isLastPage = false ;
    // when implementing loadMore

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TheMovieDBAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() ;

    TheMovieDBAPI TMDBAPI = retrofit.create(TheMovieDBAPI.class) ;

    RecyclerView recyclerView ;

    ContainerAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.container) ;

        adapter = new ContainerAdapter(this) ;

        recyclerView.setAdapter(adapter) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));


        Log.d(TAG, "onCreate: initializing actors...");
        initPopularActorsByPage(queryPage) ;
        Log.d(TAG, "onCreate: .");

    }

    //--retrofit request----------------------------------------------------------------------------
    private void initPopularActorsByPage(int page){
        Log.v(TAG, "initPopularActorsByPage: ");

        Call<ResultsPage> callPage = TMDBAPI.getPopularResults(apiKey, queryPage) ;

        callPage.enqueue(
                new Callback<ResultsPage>() {
                    @Override
                    public void onResponse(Call<ResultsPage> call, Response<ResultsPage> response) {
                        Log.d(TAG, "onResponse: ");

                        //some error handling?

                        if (queryPage < response.body().getTotalPages())
                            Log.d(TAG, "onResponse: inside if ");
                            popularActors.addAll(response.body().getResults()) ;
                            queryPage++ ;

                            totalPage = response.body().getPage() ;

                            adapter.setActors(popularActors) ; // notify data set changed?

                            //TODO
                            //for loadMore
                    }

                    @Override
                    public void onFailure(Call<ResultsPage> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                        t.printStackTrace();
                    }
                }
        ) ;



    }//---------------------------------------------------------------------------------------------

}
