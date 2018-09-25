package com.m.dan.flickrgallery.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.m.dan.flickrgallery.activities.MainActivity;
import com.m.dan.flickrgallery.R;
import com.m.dan.flickrgallery.adapteres.GalleryRecyclerAdapter;
import com.m.dan.flickrgallery.models.Photo;
import com.m.dan.flickrgallery.viewModel.GalleryViewModel;
import java.util.ArrayList;
import java.util.Objects;

public class GalleryFragment extends Fragment {

    public static final String TAG = "GalleryFragment";
    private GalleryRecyclerAdapter galleryRecyclerAdapter;
    private RecyclerView recyclerView;
    private GalleryViewModel model;
    private SearchView searchView;

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        searchView = getActivity().findViewById(R.id.toolbar_search);

        recyclerView = root.findViewById(R.id.gallery_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        model = ViewModelProviders.of(((MainActivity) Objects.requireNonNull(getContext()))).get(GalleryViewModel.class);


        galleryRecyclerAdapter = new GalleryRecyclerAdapter(getContext(), model.getPhotoList().getValue());
        recyclerView.setAdapter(galleryRecyclerAdapter);

        if(Objects.requireNonNull(model.getPhotoList().getValue()).size() == 0)
            model.searchFlickrForPhotos("water", 0, 10);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.onActionViewCollapsed();
                searchView.setIconified(true);
                model.searchFlickrForPhotos(query, 0, 10);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        model.getPhotoList().observe(this, new Observer<ArrayList<Photo>>() {

            @Override
            public void onChanged(@Nullable ArrayList<Photo> photos) {
                galleryRecyclerAdapter.notifyDataSetChanged();
            }
        });


        model.getGalleryPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer position) {
                recyclerView.scrollToPosition(position);
            }
        });

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        searchView.clearFocus();
        searchView.setIconified(true);
        searchView.onActionViewCollapsed();
    }
}
