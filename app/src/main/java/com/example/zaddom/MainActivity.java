package com.example.zaddom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zaddom.film.FilmContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements
        FilmFragment.OnListFragmentInteractionListener,
        CallDialog.OnCallDialogInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener {

    private int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DodajFilmActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DataBase db = new DataBase();
        db.downloadDataFromDatabase(((FilmFragment) getSupportFragmentManager().findFragmentById(R.id.Mainfragment)));
    }

    @Override
    public void onListFragmentClickInteraction(FilmContent.Film film, int position) {
        //Toast.makeText(getApplicationContext(), "klikniecie elementu", Toast.LENGTH_SHORT).show();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayFilmInFragment(film);
        }
        else {
            startSecondActivity(film, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(FilmContent.Film film, int position) {
        //Toast.makeText(getApplicationContext(), "przytrzymanie elementu", Toast.LENGTH_SHORT).show();
        showCallDialog(film.tytul);
    }

    public void showCallDialog(String tytul){
        CallDialog.newInstance(tytul).show(getSupportFragmentManager(),getString(R.string.call_dialog_tag));
    }

    @Override
    public void onDeleteButtonClickInteraction(int position) {
        showDeleteDialog();
        currentItemPosition = position;
    }

    @Override
    public void onEditButtonClickInteraction(FilmContent.Film film, int position) {
        // TODO run edit fragment with clicked film
        startEditActivity(film);
    }

    public static final String filmExtra = "FilmExtra";

    private void startSecondActivity(FilmContent.Film film, int position){
        Intent intent = new Intent(this, FilmInfoActivity.class);
        intent.putExtra(filmExtra, film);
        startActivity(intent);
    }

    private void startEditActivity(FilmContent.Film film){
        Intent intent = new Intent(this, EditFilmActivity.class);
        intent.putExtra(filmExtra, film);
        startActivity(intent);
    }

    public void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1){
            //FilmContent.removeFilm(currentItemPosition);

            DataBase db = new DataBase();
            db.deleteItemFromDatabase(Integer.parseInt(FilmContent.ITEMS.get(currentItemPosition).id), ((FilmFragment) getSupportFragmentManager().findFragmentById(R.id.Mainfragment)));
            db.downloadDataFromDatabase(((FilmFragment) getSupportFragmentManager().findFragmentById(R.id.Mainfragment)));
            ((FilmFragment) getSupportFragmentManager().findFragmentById(R.id.Mainfragment)).notifyDataChange();
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                ((FilmInfoFragment) getSupportFragmentManager().findFragmentById(R.id.DisplayInfofragment)).usunietyFilm();
            }
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {}

    private void displayFilmInFragment(FilmContent.Film film){
        FilmInfoFragment filmInfoFragment = ((FilmInfoFragment) getSupportFragmentManager().findFragmentById(R.id.DisplayInfofragment));
        if(filmInfoFragment != null){
            filmInfoFragment.wyswietlFilm(film, 1);
        }
    }

    @Override
    public void onCallDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Calling", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallDialogNegativeClick(DialogFragment dialog) {
    }
}