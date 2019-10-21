package com.company.p8.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.company.p8.R;
import com.company.p8.model.Content;
import com.company.p8.model.ItunesResponse;
import com.company.p8.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    List<Content> contentList = new ArrayList<>();
    ContentAdapter contentAdapter;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.songs_list);
        recyclerView.setAdapter(contentAdapter = new ContentAdapter());

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        SearchView searchView = findViewById(R.id.search_term);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String s) {
                mainViewModel.buscar(s).observe(MainActivity.this, new Observer<ItunesResponse>() {
                    @Override
                    public void onChanged(ItunesResponse itunesResponse) {
                        contentList = itunesResponse.results;
                        contentAdapter.notifyDataSetChanged();
                    }
                });

                return false;
            }
        });
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView title, artist;
        ImageView image;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.song_title);
            artist = itemView.findViewById(R.id.song_artist);
            image = itemView.findViewById(R.id.song_image);
        }
    }

    class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder>{

        @NonNull
        @Override
        public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_song, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
            Content content = contentList.get(position);

            holder.title.setText(content.trackName);
            holder.artist.setText(content.artistName);
            Glide.with(MainActivity.this).load(content.artworkUrl100).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return contentList.size();
        }
    }
}

