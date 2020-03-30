package com.example.projetv2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import modele.Cast;

public class AdapterCast extends RecyclerView.Adapter<MyViewHolderCast>  {
    private List<Cast> castList;
    private OnItemClickListener listener;
    private int n;

    public AdapterCast(List<Cast> castList, OnItemClickListener listener, int n) {
        this.castList = castList;
        this.listener = listener;
        this.n = n;
    }



    @Override
    public MyViewHolderCast onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("holdercast","skula");
            View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, parent, false);
            return new MyViewHolderCast(view, castList);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCast holder, final int position) {
        holder.display(castList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }



   /* @Override
    public void onBindViewHolder(MyViewHolderMovie holder, final int position) {
        holder.display(CastList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }*/

    @Override
    public int getItemCount() {
        if(this.castList == null){
            return 0;
        }
        return this.castList.size();
    }






    public interface OnItemClickListener{
        void onItemClick(View view, int position);

    }



}
