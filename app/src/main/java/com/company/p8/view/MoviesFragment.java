package com.company.p8.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.company.p8.model.Movie;
import com.company.p8.viewmodel.MainViewModel;
import com.company.p8.R;
import com.company.p8.model.Song;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesFragment extends Fragment {

    List<Movie> movieList = new ArrayList<>();
    MoviesAdapter moviesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.movies_list);
        recyclerView.setAdapter(moviesAdapter = new MoviesAdapter());

        ViewModelProviders.of(this).get(MainViewModel.class).searchMovies("spielberg").observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieList = movies;
                moviesAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.movie_image);
        }
    }

    class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>{

        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_movie, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            Movie movie = movieList.get(position);

            Glide.with(MoviesFragment.this).load(movie.artworkUrl100).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }
}
