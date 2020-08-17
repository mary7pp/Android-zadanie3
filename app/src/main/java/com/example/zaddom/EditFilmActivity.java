package com.example.zaddom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zaddom.film.FilmContent;

public class EditFilmActivity extends AppCompatActivity {

    private Button saveButton;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_film);

        saveButton = findViewById(R.id.saveEditbutton);

        Intent intent = this.getIntent();
        if(intent != null){
            FilmContent.Film otrzymanyFilm = intent.getParcelableExtra(MainActivity.filmExtra);
            if(otrzymanyFilm != null){
                loadData(otrzymanyFilm);
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                finish();
            }
        });
    }



    public void loadData(FilmContent.Film film){
        EditText editTytul = findViewById(R.id.tytuleditText);
        EditText editGatunek = findViewById(R.id.gatunekeditText);
        EditText editScenariusz = findViewById(R.id.scenariuszeditText);
        EditText editRezyseria = findViewById(R.id.rezyseriaeditText);
        EditText editPremiera = findViewById(R.id.premieraeditText);
        EditText editCzasTrwania = findViewById(R.id.czastrwaniaeditText);

        id = film.id;
        editTytul.setText(film.tytul);
        editGatunek.setText(film.gatunek);
        editScenariusz.setText(film.scenariusz);
        editRezyseria.setText(film.rezyseria);
        editPremiera.setText(film.premiera);
        editCzasTrwania.setText(film.czas_trwania);
    }

    private void saveData(){

        EditText editTytul = findViewById(R.id.tytuleditText);
        EditText editGatunek = findViewById(R.id.gatunekeditText);
        EditText editScenariusz = findViewById(R.id.scenariuszeditText);
        EditText editRezyseria = findViewById(R.id.rezyseriaeditText);
        EditText editPremiera = findViewById(R.id.premieraeditText);
        EditText editCzasTrwania = findViewById(R.id.czastrwaniaeditText);

        String tytul = editTytul.getText().toString();
        String gatunek = editGatunek.getText().toString();
        String scenariusz = editScenariusz.getText().toString();
        String rezyseria = editRezyseria.getText().toString();
        String premiera = editPremiera.getText().toString();
        String czasTrwania = editCzasTrwania.getText().toString();

        DataBase db = new DataBase();
        db.updateData(id, tytul, gatunek, scenariusz, rezyseria, premiera, czasTrwania);
    }
}
