package com.example.glide;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {
    private List<News> newsList = new ArrayList<>();

    public NewAdapter(){};

    public NewAdapter(List<News> newsList){
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext() , R.layout.item , null);
        NewViewHolder newViewHolder = new NewViewHolder(view);
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.mTitleTextView.setText(news.title);
        holder.mContent.setText(news.content);
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }


    class NewViewHolder extends RecyclerView.ViewHolder{
        TextView mTitleTextView;
        TextView mContent;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.text1);
            mContent = itemView.findViewById(R.id.text2);
        }
    }
}
