package com.searchactors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ContainerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0 ;
    private static final int LOADING = 1 ;

    private static final String TAG = "ContainerAdapter" ;

    private static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/w200" ;

    private Context mContext ;

    private boolean willLoad = false ;

    List<Actor> actors ;

    //constructor
    public ContainerAdapter(Context mContext) {
        Log.d(TAG, "ContainerAdapter: ");
        
        this.mContext = mContext;
        this.actors = new ArrayList<>() ;
    }

    public void setActors(List<Actor> actorsResult) {
        this.actors =  actorsResult ;
    }

    //---ViewHolders--------------------------------------------------------------------------------
    protected class ActorViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "ViewHolder";
        RelativeLayout actorLayout ;

        ImageView actorImage ;
        TextView  actorName ;
        TextView  actorPopularity ;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);

            this.actorLayout = itemView.findViewById(R.id.actor_layout) ;

            this.actorImage = itemView.findViewById(R.id.actor_image);
            this.actorName = itemView.findViewById(R.id.actor_name);
            this.actorPopularity = itemView.findViewById(R.id.actor_popularity);

            Log.d(TAG, "ViewHolder: ");
        }
    }

    protected class LoadingViewHolder extends RecyclerView.ViewHolder{

        public LoadingViewHolder(@NonNull View itemView){
            super(itemView) ;
        }
    }
    //----------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        RecyclerView.ViewHolder currViewHolder = null ;

        switch (viewType){
            case ITEM :
                View viewActor = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.actor_item, viewGroup, false );
                currViewHolder = new ActorViewHolder(viewActor) ;
                break ;

            case LOADING :
                View viewLoading = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.loading_item, viewGroup, false );
                currViewHolder = new LoadingViewHolder(viewLoading) ;
                break ;
        }

        return currViewHolder ; // might evaluate null, but the method is NonNull?
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: ");

        switch (getItemViewType(position)) { // TODO
            case ITEM :
                ActorViewHolder actorViewHolder = (ActorViewHolder) viewHolder;

                Actor currActor = actors.get(position);

                Glide.with(mContext)
                        .asBitmap()
                        .load(BASE_IMG_URL + currActor.getProfilePath())
                        .into(actorViewHolder.actorImage);

                //name
                actorViewHolder.actorName.setText(currActor.getName());

                //popularity
                actorViewHolder.actorPopularity.setText(currActor.getPopularity());

                break ;

            case LOADING :
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder ;
                // what TODO
                break ;
        }

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        
        return actors == null ? 0 : actors.size() ;
    }


    @Override
    public int getItemViewType(int position){
        return (position == actors.size() - 1 && willLoad) ? LOADING : ITEM ;
    }

    //----------------------------------------------------------------------------------------------

    public void addDummyActor(){
        actors.add(new Actor()) ;
        willLoad = true ;
    }

    public void removeDummyActor(){
        actors.remove(actors.size()-1) ; // check.
        willLoad = false ;
    }

}
