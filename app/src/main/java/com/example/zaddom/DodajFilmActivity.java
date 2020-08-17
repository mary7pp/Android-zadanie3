package com.example.zaddom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zaddom.film.FilmContent;

import static com.example.zaddom.film.FilmContent.ITEMS;

public class DodajFilmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_film);
        Button dodajFilmButton = findViewById(R.id.saveEditbutton);
        dodajFilmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DodajFilm();
            }
        });
    }

    private void DodajFilm(){
        EditText editTytul = findViewById(R.id.tytuleditText);
        EditText editGatunek = findViewById(R.id.gatunekeditText);
        EditText editScenariusz = findViewById(R.id.scenariuszeditText);
        EditText editRezyseria = findViewById(R.id.rezyseriaeditText);
        EditText editPremiera = findViewById(R.id.premieraeditText);
        EditText editCzasTrwania = findViewById(R.id.czastrwaniaeditText);

        String mTytul = editTytul.getText().toString();
        String mGatunek = editGatunek.getText().toString();
        String mScenariusz = editScenariusz.getText().toString();
        String mRezyseria = editRezyseria.getText().toString();
        String mPremiera = editPremiera.getText().toString();
        String mCzasTrwania = editCzasTrwania.getText().toString();

        /*
        //Sprawdzenie poprawnosci danych
        if(mImie.isEmpty()){
            editImie.setError(getString(R.string.puste_pole));
            return;
        }
        if(mNazwisko.isEmpty()){
            editNazwisko.setError(getString(R.string.puste_pole));
            return;
        }
        if(mData.isEmpty()){
            editData.setError(getString(R.string.puste_pole));
            return;
        }
        else{
            if(!mData.matches("([0-9]{2})/([0-9]){2}/([0-9]){4}")){
                editData.setError(getString(R.string.format_data));
                return;
            }

            String day = Character.toString(mData.charAt(0)) + Character.toString(mData.charAt(1));
            if(Integer.parseInt(day) > 31){
                editData.setError(getString(R.string.za_duza_wartosc));
                return;
            }
            String month = Character.toString(mData.charAt(3)) + Character.toString(mData.charAt(4));
            if(Integer.parseInt(month) > 12){
                editData.setError(getString(R.string.za_duza_wartosc));
                return;
            }
        }

        if(mNumer.isEmpty()){
            editNumer.setError(getString(R.string.puste_pole));
            return;
        }
        else{
            if(!mNumer.matches("([0-9]){9}")){
                editNumer.setError(getString(R.string.format_numer));
                return;
            }
        }

        Calendar c = Calendar.getInstance();
        int biezacy_rok = c.get(Calendar.YEAR);
        String year = Character.toString(mData.charAt(6)) + Character.toString(mData.charAt(7)) + Character.toString(mData.charAt(8)) + Character.toString(mData.charAt(9));
        if(Integer.parseInt(year) > biezacy_rok){
            editData.setError(getString(R.string.za_duza_wartosc));
            return;
        }
        */
        FilmContent.Film newFilm = new FilmContent.Film(String.valueOf(FilmContent.lastID), mTytul, mGatunek, mScenariusz, mRezyseria, mPremiera, mCzasTrwania);

        DataBase db = new DataBase();
        db.addItemToDatabse(newFilm);

        ITEMS.add(newFilm);

        editTytul.setText("");
        editGatunek.setText("");
        editScenariusz.setText("");
        editRezyseria.setText("");
        editPremiera.setText("");
        editCzasTrwania.setText("");

        finish();
    }
}