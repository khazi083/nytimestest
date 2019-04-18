package com.nytimes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nytimes.R;
import com.nytimes.view.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


import com.nytimes.Interface.ItemClickListener;
import com.nytimes.Model.Result;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>  {

    private Context mContext;
    private List<Result> mResults;


    public MyRecyclerAdapter(Context context, List<Result> results) {
        mContext = context;
        mResults = results;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.binding.newsTitle.setText(mResults.get(i).getTitle());
        holder.binding.newsAuthor.setText(mResults.get(i).getByline());
        holder.binding.newsSection.setText(mResults.get(i).getSection());
        holder.binding.newsDate.setText(mResults.get(i).getPublishedDate());


        String urlImage = mResults.get(i).getMedia().get(0).getMediaMetadata().get(1).getUrl();

        Picasso.get()
                .load(urlImage)
                .error(R.drawable.article)
                .placeholder(R.drawable.progress)
                .into(holder.binding.newsPicture);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent detail = new Intent(mContext,DetailActivity.class);
                detail.putExtra("SELECTED_NEWS",mResults.get(position));
                detail.putExtra("url", mResults.get(position).getMedia().get(0).getMediaMetadata().get(1).getUrl());
                mContext.startActivity(detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }
}
