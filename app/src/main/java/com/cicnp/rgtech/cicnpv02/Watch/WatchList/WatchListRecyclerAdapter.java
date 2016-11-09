package com.cicnp.rgtech.cicnpv02.Watch.WatchList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cicnp.rgtech.cicnpv02.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class WatchListRecyclerAdapter extends RecyclerView.Adapter<WatchListRecyclerAdapter.ViewHolder> {

    private List<WatchListRecyclerDataWrapper> recyclerDataList;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView name;
        public TextView priority;

        public ViewHolder(View view){
            super(view);

            image = (ImageView) view.findViewById(R.id.watchlist_recycler_row_image);
            name = (TextView) view.findViewById(R.id.watchlist_recycler_row_name);
            priority = (TextView) view.findViewById(R.id.watchlist_recycler_row_priority);
        }
    }

    public WatchListRecyclerAdapter(List<WatchListRecyclerDataWrapper> recyclerDataList){
        this.recyclerDataList = recyclerDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.watchlist_recycler_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(recyclerDataList.get(position).name.toString());
        holder.priority.setText(recyclerDataList.get(position).secondaryInfo.toString());

        Picasso.with(context)
                .load(recyclerDataList.get(position).imageUrl)
                .resize((int)context.getResources().getDimension(R.dimen.watchlist_imageSize),(int)context.getResources().getDimension(R.dimen.watchlist_imageSize))
                .placeholder(R.drawable.dot)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return recyclerDataList.size();
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, WatchListRecyclerDataWrapper data) {
        recyclerDataList.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(String data) {
        int position = recyclerDataList.indexOf(data);
        recyclerDataList.remove(position);
        notifyItemRemoved(position);
    }
}
