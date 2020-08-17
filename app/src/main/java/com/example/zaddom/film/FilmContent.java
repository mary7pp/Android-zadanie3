package com.example.zaddom.film;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmContent {
    public static final List<Film> ITEMS = new ArrayList<Film>();
    public static int lastID = 0;

    //public static final Map<String, Film> ITEM_MAP = new HashMap<String, Film>();

    public static void removeFilm(int position){
        ITEMS.remove(position);
    }

    public static class Film implements Parcelable {
        public final String id;
        public final String tytul;
        public final String gatunek;
        public final String scenariusz;
        public final String rezyseria;
        public final String premiera;
        public final String czas_trwania;

        public Film(String id, String tytul, String gatunek, String scenariusz, String rezyseria, String premiera, String czas_trwania) {
            this.id = id;
            this.tytul = tytul;
            this.gatunek = gatunek;
            this.scenariusz = scenariusz;
            this.rezyseria = rezyseria;
            this.premiera = premiera;
            this.czas_trwania = czas_trwania;
            lastID++;
        }

        protected Film(Parcel in) {
            id = in.readString();
            tytul = in.readString();
            gatunek = in.readString();
            scenariusz = in.readString();
            rezyseria = in.readString();
            premiera = in.readString();
            czas_trwania = in.readString();
        }

        public static final Creator<Film> CREATOR = new Creator<Film>() {
            @Override
            public Film createFromParcel(Parcel in) {
                return new Film(in);
            }

            @Override
            public Film[] newArray(int size) {
                return new Film[size];
            }
        };

        @Override
        public String toString() {
            return tytul;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(tytul);
            dest.writeString(gatunek);
            dest.writeString(scenariusz);
            dest.writeString(rezyseria);
            dest.writeString(premiera);
            dest.writeString(czas_trwania);
        }
    }
}