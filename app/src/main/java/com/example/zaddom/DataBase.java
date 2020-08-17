package com.example.zaddom;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.zaddom.film.FilmContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    FirebaseFirestore db;

    public DataBase(){
        db = FirebaseFirestore.getInstance();
    }

    public void addItemToDatabse(FilmContent.Film newFilm) {
        db.collection("Filmy").document(newFilm.id).set(newFilm);
    }

    public void deleteItemFromDatabase(int filmId, final FilmFragment filmFragment){
        db.collection("Filmy").document(Integer.toString(filmId)).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    filmFragment.notifyDataChange();
            }
        });
    }

    public  void downloadDataFromDatabase(final FilmFragment filmFragment){
        List<FilmContent.Film> data = new ArrayList<FilmContent.Film>();

        db.collection("Filmy").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    FilmContent.ITEMS.clear();

                    for(QueryDocumentSnapshot document : task.getResult()){
                        Map<String, Object> film = new HashMap<>();

                        film = document.getData();

                        String id = film.get("id").toString();
                        String tytul = film.get("tytul").toString();
                        String gatunek = film.get("gatunek").toString();
                        String scenariusz = film.get("scenariusz").toString();
                        String rezyseria = film.get("rezyseria").toString();
                        String premiera = film.get("premiera").toString();
                        String czastrwania = film.get("czas_trwania").toString();


                        FilmContent.lastID = Integer.parseInt(id);
                        FilmContent.Film filmFromDatabase = new FilmContent.Film(id, tytul, gatunek, scenariusz, rezyseria, premiera, czastrwania);
                        FilmContent.ITEMS.add(filmFromDatabase);
                    }
                    filmFragment.notifyDataChange();
                }
            }
        });

    }

    void updateData(String id, String tytul, String gatunek, String scenariusz, String rezyseria, String premiera, String czastrwania){
        DocumentReference docRef = db.collection("Filmy").document(id);

        docRef.update("tytul", tytul,
                "gatunek", gatunek,
                "scenariusz", scenariusz,
                "rezyseria", rezyseria,
                "premiera", premiera,
                "czas_trwania", czastrwania);
    }

}
