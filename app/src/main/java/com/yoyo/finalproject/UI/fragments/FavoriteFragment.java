//package com.yoyo.finalproject.UI.fragments;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.yoyo.finalproject.R;
//import com.yoyo.finalproject.UI.activities.DetailActivity;
//import com.yoyo.finalproject.UI.adapters.FavoriteAdapter;
//import com.yoyo.finalproject.UI.adapters.clickListeners.OnFavoriteClickListener;
//import com.yoyo.finalproject.data.local.FavoriteHelper;
//import com.yoyo.finalproject.data.models.Favorite;
//
//public class FavoriteFragment extends Fragment implements OnFavoriteClickListener {
//
//    private FavoriteHelper helper;
//    private TextView tvError;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
//        helper = new FavoriteHelper(getContext());
//        tvError = view.findViewById(R.id.tv_error);
//        RecyclerView recyclerView = view.findViewById(R.id.rv_favorite);
//        setRecyclerView(recyclerView);
//        return view;
//    }
//
//    private void setRecyclerView(RecyclerView recyclerView) {
//        if (helper.selectAll().isEmpty()) {
//            tvError.setText(getString(R.string.favorite_empty));
//        } else {
//            tvError.setVisibility(View.GONE);
//            FavoriteAdapter adapter = new FavoriteAdapter(helper.selectAll());
//            adapter.setClickListener(FavoriteFragment.this);
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        }
//    }
//
//    @Override
//    public void onClick(Favorite favorite) {
//        Intent detailActivity = new Intent(getContext(), DetailActivity.class);
//        detailActivity.putExtra("ID", favorite.getId());
//        detailActivity.putExtra("SELECTED_FRAGMENT", "favorite");
//        startActivity(detailActivity);
//    }
//}