package com.example.projetv2.ui.favoris;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetv2.MovieDetails;
import com.example.projetv2.R;
import com.example.projetv2.RecyclerViewAdapter;
import com.example.projetv2.ui.films.FilmFragment;

import java.util.List;

import modele.Movie;

public class FavorisFragment extends Fragment {

    public static List<Movie> listFavoris;
    public static RecyclerView recyclerFavoris;
    public static final String FAVORIS = "favoris";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favoris, container, false);

        recyclerFavoris = root.findViewById(R.id.recyclerFavoris);
      //  LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
       // recyclerFavoris.setLayoutManager(horizontalLayoutManager);
        recyclerFavoris.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        listFavoris = FilmFragment.listFavoris;
        startRecyclerFavoris();

       /* final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }


    private void startRecyclerFavoris(){
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(listFavoris, new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {


                Toast.makeText(getActivity(),"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getActivity(), MovieDetails.class);

                Bundle b = new Bundle();
                Movie movie = (Movie) listFavoris.get(position);
                String nomSelect = movie.getTitle();
                b.putString(FAVORIS, FAVORIS);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },2);
        recyclerFavoris.setAdapter(recyclerViewAdapter);

        recyclerFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("click","je viens de cliquer sur ..");
            }
        });
    }
}