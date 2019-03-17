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

    private static final String apiKey = "90f9f56e299e21f06338b0197a5ff6f6" ;
    // TODO
    // private boolean isLastPage = false ;
    // when implementing loadMore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: initializing actors...");
        initPopularActorsByPage(queryPage) ;
        Log.d(TAG, "onCreate: .");

    }

    //--retrofit request----------------------------------------------------------------------------
    private void initPopularActorsByPage(int page){
        Log.v(TAG, "initPopularActorsByPage: ");
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDBAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        TheMovieDBAPI TMDBAPI = retrofit.create(TheMovieDBAPI.class) ;

        Call<ResultsPage> callPage = TMDBAPI.getPopularResults(apiKey, queryPage) ;

        callPage.enqueue(
                new Callback<ResultsPage>() {
                    @Override
                    public void onResponse(Call<ResultsPage> call, Response<ResultsPage> response) {
                        Log.d(TAG, "onResponse: ");
                        if (queryPage < response.body().getTotalPages())
                            Log.d(TAG, "onResponse: inside if ");
                            popularActors.addAll(response.body().getResults()) ;
                            queryPage++ ;
                        Log.d(TAG, "onResponse: " + popularActors.get(0).getName());
                            //TODO
                            //for loadMore

                        Log.d(TAG, "initPopularActorsByPage: initializing recycler view ") ;
                        initRecyclerView();
                    }

                    @Override
                    public void onFailure(Call<ResultsPage> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                        t.printStackTrace();
                    }
                }
        ) ;



    }//---------------------------------------------------------------------------------------------
    //--recycler view-------------------------------------------------------------------------------
    private void initRecyclerView (){
        Log.v(TAG, "initRecyclerView: ");
        
        RecyclerView recyclerView = findViewById(R.id.container) ;

        ContainerAdapter adapter = new ContainerAdapter(this) ;
        adapter.setActors(popularActors) ;

        recyclerView.setAdapter(adapter) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
    }
}
