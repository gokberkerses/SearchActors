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

public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ViewHolder> {

    private static final String TAG = "ContainerAdapter" ;

    private Context mContext ;

    // TODO
    // ResultsPage or list<Actor> ? probably second.
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "ViewHolder";
        RelativeLayout actorLayout ;

        ImageView actorImage ;
        TextView  actorName ;
        TextView  actorPopularity ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.actorLayout = itemView.findViewById(R.id.actor_layout) ;

            this.actorImage = itemView.findViewById(R.id.actor_image);
            this.actorName = itemView.findViewById(R.id.actor_name);
            this.actorPopularity = itemView.findViewById(R.id.actor_popularity);

            Log.d(TAG, "ViewHolder: ");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.actor_item, viewGroup, false );
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: ");

        String BASE_URL = "https://image.tmdb.org/t/p/w500"; // play with size, for performance

        //TODO
        //from which object .load() will get the uri ?
        Glide.with(mContext)
                .asBitmap()
                .load(BASE_URL + actors.get(position).getProfilePath())
                .into(viewHolder.actorImage) ;

        //name
        viewHolder.actorName.setText(actors.get(position).getName()) ;

        //popularity
        viewHolder.actorPopularity.setText(actors.get(position).getPopularity());

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        
        return actors.size() ;
    }


}
