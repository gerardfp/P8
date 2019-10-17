package com.company.p8.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class SongsFragment extends Fragment {

    List<Song> songList = new ArrayList<>();
    SongsAdapter songsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.songs_list);
        recyclerView.setAdapter(songsAdapter = new SongsAdapter());

        ViewModelProviders.of(this).get(MainViewModel.class).searchSongs("kings of leon").observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> contents) {
                songList = contents;
                songsAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        TextView title, artist;
        ImageView image;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.song_title);
            artist = itemView.findViewById(R.id.song_artist);
            image = itemView.findViewById(R.id.song_image);
        }
    }

    class SongsAdapter extends RecyclerView.Adapter<SongViewHolder>{

        @NonNull
        @Override
        public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SongViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_song, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
            Song song = songList.get(position);

            holder.title.setText(song.trackName);
            holder.artist.setText(song.artistName);
            Glide.with(SongsFragment.this).load(song.artworkUrl100).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return songList.size();
        }
    }
}
