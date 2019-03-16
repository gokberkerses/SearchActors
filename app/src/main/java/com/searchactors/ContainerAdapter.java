package com.searchactors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ViewHolder> {

    private static final String TAG = "ContainerAdapter" ;

    private Context mContext ;

    // TODO
    // ResultsPage or list<Actor> ? probably second.
    List<Actor> actors ;

    //constructor
    public ContainerAdapter(Context mContext, List<Actor> actors) {
        this.mContext = mContext;
        this.actors = actors; //shallow copy or deep copy?
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.actor_item, viewGroup, false );
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

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
        return actors.size() ;
    }


}
