package com.example.projetv2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import modele.Movie;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolderMovie>  {
    private List<Movie> MovieList;
    private OnItemClickListener listener;

    public RecyclerViewAdapter(List<Movie> movieList, OnItemClickListener listener) {
        MovieList = movieList;
        this.listener = listener;
    }


    @Override
    public MyViewHolderMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolderMovie(view,MovieList);
    }

    @Override
    public void onBindViewHolder(MyViewHolderMovie holder, final int position) {
        holder.display(MovieList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.MovieList == null){
            return 0;
        }
        return this.MovieList.size();
    }



    public void deleteMovie(int index,List<Movie> movieList ){
        this.MovieList = movieList;
        this.MovieList.remove(index);
        notifyDataSetChanged();
    }
    public void notifyAdapter(){
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);

    }



}
