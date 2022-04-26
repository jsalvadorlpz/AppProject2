package com.example.entrega3.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.entrega3.R;
import com.example.entrega3.model.ItemList;

public class DetalleMovie extends Fragment {
    TextView titulo,genre,date,sinopsis,language;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,@Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.detalle_movie_fragment,container, false);
        titulo = view.findViewById(R.id.Title);
        genre = view.findViewById(R.id.Genre);
        date = view.findViewById(R.id.date);
        sinopsis = view.findViewById(R.id.sinopsis);
        language = view.findViewById(R.id.director);
        //crear objeto bundle para recibir parametros
        Bundle movie = getArguments();
        ItemList movie2 = null;
        if(movie !=null){
            movie2 = (ItemList) movie.getSerializable("objeto");
            //establecer datos
            titulo.setText(movie2.getTitulo());
            genre.setText(movie2.getGenrer());
            date.setText(movie2.getRelease());
            sinopsis.setText(movie2.getSinopsis());
            language.setText(movie2.getLanguage());

        }
        return view;
    }

}
