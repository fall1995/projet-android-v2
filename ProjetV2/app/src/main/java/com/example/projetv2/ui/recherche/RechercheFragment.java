package com.example.projetv2.ui.recherche;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetv2.MovieDetails;
import com.example.projetv2.R;
import com.example.projetv2.RecyclerViewAdapter;
import com.example.projetv2.ui.films.FilmFragment;

import java.util.ArrayList;
import java.util.List;

import modele.Movie;
import modele.MovieCollection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RetrofitClientInstance;
import service.TmdbService;

public class RechercheFragment extends Fragment {

    public ArrayAdapter<String> adapter;

    public EditText editText;
    //public ListView listView;
    public static List<Movie> listMovie;
    public List<Movie> listFixe;
    public String[] items;
    public ArrayList<String> listItems;
    private RecyclerView recyclerView;
    public RecyclerViewAdapter recyclerViewAdapter;
    public static final String SEARCH = "search";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       // fetchTmdbData("S");

       // dashboardViewModel =
               // ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recherche, container, false);

       /* listFixe = new ArrayList<Movie>();
        listFixe.addAll(FilmFragment.listMovie);
        listMovie = new ArrayList<Movie>();
        listMovie.addAll(FilmFragment.listMovie);*/
        recyclerView = root.findViewById(R.id.recycler_search);
       // listView =  root.findViewById(R.id.listview);
        editText = root.findViewById(R.id.txtsearch);
        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
        editText.setOnFocusChangeListener(ofcListener);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
      //  initList();




        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    Log.i("log77", " fck 12"  );
                    // reset listview
                    if (listMovie != null){
                        listMovie.clear();

                    }
                    recyclerViewAdapter.notifyAdapter();

                   // initList();
                } else{


                   // recyclerViewAdapter.notifyDataSetChanged();
                    fetchTmdbData(s.toString());
                     //recyclerViewAdapter.notifyDataSetChanged();
                //    searchItem(s.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



       /* final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
    private void fetchTmdbData(String search) {
        TmdbService tmdbService = RetrofitClientInstance.getlnstance().create(TmdbService.class);

        tmdbService.getSearch(FilmFragment.key,search).enqueue(new Callback<MovieCollection>() {
            @Override
            public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {

                listMovie = response.body().getMovieList();
                  Log.i("log990", "taille "+listMovie.size()  );
//                Log.i("log", listMovie.get(0).getTitle()   );
              //  recyclerViewAdapter.notifyAdapter();
                startRecyclerSearch();
            }

            @Override
            public void onFailure(Call<modele.MovieCollection> call, Throwable t) {
                Log.i("test", "marche paaaaas");
            }

        });
    }

        public void searchItem(String textToSearch){
        Log.i("log79", " dans search item"  );


       /* for(String item:items){

            if(!item.contains(textToSearch)){
                listMovie.remove(item);

            }

        }*/

       for(int i=0;i<listMovie.size();i++){
           if(!listMovie.get(i).getTitle().contains(textToSearch)){
               Log.i("log100","size tab " +listMovie.size() );
               recyclerViewAdapter.deleteMovie(i,listMovie);

               //listSuppr.add(listMovie.get(i));
           }
       }

      //  recyclerViewAdapter.notifyDataSetChanged();

    }


    public void startRecyclerSearch(){

        recyclerViewAdapter = new RecyclerViewAdapter(listMovie, new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(getActivity(),"movie selectionnée en position " + position, Toast.LENGTH_LONG).show();
                Intent movieClickActivity = new Intent(getActivity(), MovieDetails.class);
                Bundle b = new Bundle();
                Movie movie = (Movie) listMovie.get(position);
                String nomSelect = movie.getTitle();
                b.putString(SEARCH, SEARCH);
                b.putInt("pos",position);
                movieClickActivity.putExtras(b); //Put your id to your next Intent
                startActivity(movieClickActivity);

            }
        },1);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","je viens de cliquer sur ..");
            }
        });

    }
    private class MyFocusChangeListener implements View.OnFocusChangeListener {

        public void onFocusChange(View v, boolean hasFocus){

            if(v.getId() == R.id.txtsearch && !hasFocus) {

                InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }
    }
}