package com.searchactors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    protected List<Actor> popularActors = new ArrayList<>() ;
    protected int queryPage = 1 ;
    // TODO
    // private boolean isLastPage = false ;
    // when implementing loadMore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPopularActorsByPage(queryPage) ;
        initRecyclerView();
    }

    //--retrofit request----------------------------------------------------------------------------
    private void initPopularActorsByPage(int page){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDBAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;

        TheMovieDBAPI TMDBAPI = retrofit.create(TheMovieDBAPI.class) ;

        Call<ResultsPage> callPage = TMDBAPI.getPopularResults(Integer.toString(page)) ;

        callPage.enqueue(
                new Callback<ResultsPage>() {
                    @Override
                    public void onResponse(Call<ResultsPage> call, Response<ResultsPage> response) {
                        popularActors.addAll(response.body().getResults()) ;
                        if (queryPage < response.body().getTotalPages())
                            queryPage++ ;
                            //TODO
                            //for loadMore
                    }

                    @Override
                    public void onFailure(Call<ResultsPage> call, Throwable t) {
                        // TODO
                        // toast?
                    }
                }
        ) ;
    }//---------------------------------------------------------------------------------------------
    //--recycler view-------------------------------------------------------------------------------
    private void initRecyclerView (){
        RecyclerView recyclerView = findViewById(R.id.container) ;
        ContainerAdapter adapter = new ContainerAdapter(this, popularActors) ;
        recyclerView.setAdapter(adapter) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
    }
}
