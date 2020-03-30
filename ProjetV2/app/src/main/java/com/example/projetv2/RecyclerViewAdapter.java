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
    private List<Movie> movieList;
    private OnItemClickListener listener;

    private int n;

    public RecyclerViewAdapter(List<Movie> movieList, OnItemClickListener listener,int n) {
        this.movieList = movieList;
        this.listener = listener;
        this.n = n;
    }




    @Override
    public MyViewHolderMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (n==1){
            View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recherche, parent, false);
            return new MyViewHolderMovie(view, movieList,n);
        }else if(n==2){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toutafficher, parent, false);
            return new MyViewHolderMovie(view, movieList,n);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new MyViewHolderMovie(view, movieList,n);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolderMovie holder, final int position) {
        holder.display(movieList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.movieList == null){
            return 0;
        }
        return this.movieList.size();
    }



    public void deleteMovie(int index,List<Movie> movieList ){
        this.movieList = movieList;
        this.movieList.remove(index);
        notifyDataSetChanged();
    }
    public void notifyAdapter(){
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);

    }



}
