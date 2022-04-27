package com.example.entrega3.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.entrega3.Adapter.RecyclerAdapter;
import com.example.entrega3.MovieResults;
import com.example.entrega3.R;
import com.example.entrega3.TheMovieDatasetApi;
import com.example.entrega3.iComunicaFragments;
import com.example.entrega3.model.ItemList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    List<ItemList> itemList;

    Activity actividad;
    iComunicaFragments interfaceComunicateFragmets;


    //para las imagenes, como el poster_path solo nos da un trozo del link que necesiamtos, tenemos que tener la primera
    //parte que es generica a todos
    public String url_imagenes = "https://image.tmdb.org/t/p/w500";

    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public String API_KEY = "65b0f0c1dca6b0957d34d1fceaf3107a";
    public static String  LANGUAGE = "en-US";
    public static String CATEGORY="popular";
    public int id;
    public String titulo,release,poster_path,sinopsis,language;
    public List<String> titulos, releases,generos3,sinopsisList,languages, poster_paths;
    public List<Integer> generos;
    public List<List<Integer>> generos2;
    public int cantidad2,j,i  = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("","entra al oncreate");
        View view = inflater.inflate(R.layout.main_fragment,container, false);
        //initViews
        recyclerView = view.findViewById(R.id.recyclerview_movies);
        itemList = new ArrayList<>();

        titulos = new ArrayList<String>();
        releases = new ArrayList<String>();
        generos = new ArrayList<Integer>();
        generos2 = new ArrayList<List<Integer>>();
        generos3 = new ArrayList<String>();
        sinopsisList = new ArrayList<>();
        languages = new ArrayList<>();
        poster_paths = new ArrayList<String>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TheMovieDatasetApi myInterface = retrofit.create(TheMovieDatasetApi.class);
        Call<MovieResults> call = myInterface.listOfMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                Log.e("","entra en el onResponse");
                MovieResults results = response.body();
                List<MovieResults.ResultsBean> listOfMovies = results.getResults();

                int cantidad = listOfMovies.size();
                cantidad2 = cantidad;

                while (i < cantidad) {
                    MovieResults.ResultsBean Movie = listOfMovies.get(i);
                    titulo = (String) Movie.getOriginal_title();
                    release = (String) Movie.getRelease_date();
                    generos = (List<Integer>) Movie.getGenre_ids();
                    sinopsis = (String) Movie.getOverview();
                    language= (String)Movie.getOriginal_language();
                    poster_path= (String) Movie.getPoster_path();
                    //Log.e("",Movie.getOriginal_title());
                    titulos.add(titulo);
                    releases.add(release);
                    generos2.add(generos);
                    sinopsisList.add(sinopsis);
                    languages.add(language);
                   poster_paths.add(poster_path);
                    i = i + 1;
                }
                i = 0;
                while (i < cantidad) {
                    generos3.add(String.valueOf(generos2.get(i)));
                    i++;
                }
                Log.e("hay: ", Integer.toString(cantidad));
                initValues();
            }
            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {

            }
        });
        return view;
    }


    private void initValues(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = getItems();
        recyclerAdapter = new RecyclerAdapter(getContext(),itemList);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo2 = itemList.get(recyclerView.getChildAdapterPosition(view)).getTitulo();
                Toast.makeText(getContext(),"selecciono"+titulo2,Toast.LENGTH_SHORT).show();
                interfaceComunicateFragmets.enviarItemList(itemList.get(recyclerView.getChildAdapterPosition(view)));
            }
        });


    }
    private List<ItemList> getItems(){
        List<ItemList> itemLists = new ArrayList<>();
        while(j<cantidad2) {

            itemLists.add(new ItemList(titulos.get(j), releases.get(j),generos3.get(j),sinopsisList.get(j),languages.get(j),poster_paths.get(j)));
            j+=1;
        }


        return itemLists;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad = (Activity) context;
            interfaceComunicateFragmets = (iComunicaFragments) this.actividad;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
