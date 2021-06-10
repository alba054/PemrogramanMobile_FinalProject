package com.yoyo.finalproject.UI.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoyo.finalproject.ImageSize;
import com.yoyo.finalproject.R;
import com.yoyo.finalproject.UI.activities.DetailActivity;
import com.yoyo.finalproject.UI.adapters.FavoriteMovieAdapter;
import com.yoyo.finalproject.UI.adapters.clickListeners.OnItemClickListener;
import com.yoyo.finalproject.data.local.models.FavoriteMovie;
import com.yoyo.finalproject.data.local.models.FavoriteTv;
import com.yoyo.finalproject.data.models.Movie;
import com.yoyo.finalproject.data.models.TvShow;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class FavoriteMovieFragment extends Fragment implements OnItemClickListener {
    private RecyclerView recyclerView;
    private Realm backgroundThreadRealm;
    private FavoriteMovieAdapter adapter;


    public FavoriteMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Realm.init(getContext());

        String realmName = "final project";
        RealmConfiguration config = new RealmConfiguration.Builder().allowWritesOnUiThread(true).
                name(realmName).
                build();

        backgroundThreadRealm = Realm.getInstance(config);

        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        recyclerView = view.findViewById(R.id.rv_favorite);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        List<FavoriteMovie> favoriteMovies = (List) backgroundThreadRealm.where(FavoriteMovie.class).findAll();

        Log.d("Favorite Movies", favoriteMovies.get(0).getTitle());
        adapter = new FavoriteMovieAdapter(favoriteMovies);
        Log.d("Adapter", adapter.toString());
        adapter.setClickListener(FavoriteMovieFragment.this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }


    @Override
    public void onClick(TvShow tvShow) {

    }

    @Override
    public void onClick(Movie movie) {
        Intent detailActivity = new Intent(getContext(), DetailActivity.class);
        detailActivity.putExtra("ID", movie.getId());
        detailActivity.putExtra("TITLE", movie.getTitle());
        detailActivity.putExtra("POSTER_PATH", movie.getPosterPath(ImageSize.W154));
        detailActivity.putExtra("SELECTED_FRAGMENT", "movie");
        startActivity(detailActivity);
    }

    @Override
    public void onClick(FavoriteMovie movie) {

    }

    @Override
    public void onClick(FavoriteTv tv) {

    }


}
