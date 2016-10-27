package com.cicnp.rgtech.cicnpv02.NavigationBar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cicnp.rgtech.cicnpv02.R;
import java.util.List;


public class NavigationRecyclerAdapter extends RecyclerView.Adapter<NavigationRecyclerAdapter.ViewHolder> {

    private List<NavigationRecyclerDataWrapper> recyclerDataList;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView image;
        public TextView subtitle;

        public ViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.navigation_recycler_row_text);
            image = (ImageView) view.findViewById(R.id.navigation_recycler_row_image);
        }
    }

    public NavigationRecyclerAdapter(List<NavigationRecyclerDataWrapper> recyclerDataList){
        this.recyclerDataList = recyclerDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.navigation_recycler_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(recyclerDataList.get(position).title.toString());

        String uri = "@drawable/" + recyclerDataList.get(position).image;
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        holder.image.setImageResource(imageResource);
    }

    @Override
    public int getItemCount() {
        return recyclerDataList.size();
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, NavigationRecyclerDataWrapper data) {
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
