package com.example.zaddom;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zaddom.FilmFragment.OnListFragmentInteractionListener;
import com.example.zaddom.film.FilmContent;
import com.example.zaddom.film.FilmContent.Film;

import java.util.List;

public class MyFilmRecyclerViewAdapter extends RecyclerView.Adapter<MyFilmRecyclerViewAdapter.ViewHolder> {

    private final List<Film> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyFilmRecyclerViewAdapter(List<Film> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Film film = mValues.get(position);
        holder.mItem = film;
        holder.mtytulView.setText(film.tytul);


        holder.mdeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "del txt", Toast.LENGTH_SHORT).show();
                mListener.onDeleteButtonClickInteraction(position);
            }
        });

        holder.meditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onEditButtonClickInteraction(holder.mItem, position);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(holder.mItem, position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mtytulView;
        public final ImageButton mdeleteButton;
        public final ImageButton meditButton;
        public Film mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mtytulView = view.findViewById(R.id.tytulView);
            mdeleteButton = view.findViewById(R.id.deleteButton);
            meditButton = view.findViewById(R.id.editButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mtytulView.getText() + "'";
        }
    }
}