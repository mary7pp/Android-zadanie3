package com.example.zaddom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zaddom.film.FilmContent;

public class FilmInfoFragment extends Fragment {

    public FilmInfoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film_info, container, false);
    }

    public void usunietyFilm(){
        FragmentActivity activity = getActivity();

        TextView tytul = activity.findViewById(R.id.TytultextView);
        TextView gatunek = activity.findViewById(R.id.GatunektextView);
        TextView scenariusz = activity.findViewById(R.id.ScenariusztextView);
        TextView rezyseria = activity.findViewById(R.id.RezyseriatextView);
        TextView premiera = activity.findViewById(R.id.PremieratextView);
        TextView czastrwania = activity.findViewById(R.id.CzastrwaniatextView);

        tytul.setText("");
        gatunek.setText("");
        scenariusz.setText("");
        rezyseria.setText("");
        premiera.setText("");
        czastrwania.setText("");

    }

    public void wyswietlFilm(FilmContent.Film film, int widok){
        FragmentActivity activity = getActivity();

        TextView tytul = activity.findViewById(R.id.TytultextView);
        TextView gatunek = activity.findViewById(R.id.GatunektextView);
        TextView scenariusz = activity.findViewById(R.id.ScenariusztextView);
        TextView rezyseria = activity.findViewById(R.id.RezyseriatextView);
        TextView premiera = activity.findViewById(R.id.PremieratextView);
        TextView czastrwania = activity.findViewById(R.id.CzastrwaniatextView);

        tytul.setText("Tytuł: " + film.tytul);
        gatunek.setText("Gatunek: " + film.gatunek);
        scenariusz.setText("Scenariusz: "+ film.scenariusz);
        rezyseria.setText("Reżyseria: " + film.rezyseria);
        premiera.setText("Premiera: " + film.premiera);
        czastrwania.setText("Czas trwania: " + film.czas_trwania);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            FilmContent.Film otrzymanyFilm = intent.getParcelableExtra(MainActivity.filmExtra);
            if(otrzymanyFilm != null){
                wyswietlFilm(otrzymanyFilm, 0);
            }
        }
    }
}