package com.m.dan.flickrgallery.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.m.dan.flickrgallery.R;
import com.m.dan.flickrgallery.adapteres.GalleryRecyclerAdapter;
import com.m.dan.flickrgallery.asysncTasks.PhotosTask;
import com.m.dan.flickrgallery.models.Photos;

public class GalleryFragment extends Fragment {

    public static final String TAG = "GalleryFragment";
    public static final String SAVED_PHOTOS = "SAVED_PHOTOS";
    private GalleryRecyclerAdapter galleryRecyclerAdapter;
    private RecyclerView recyclerView;
    private Photos mPhotos;

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView = root.findViewById(R.id.gallery_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(savedInstanceState != null){
            mPhotos = savedInstanceState.getParcelable(SAVED_PHOTOS);
            galleryRecyclerAdapter = new GalleryRecyclerAdapter(getContext(), mPhotos.getPhotos());
            recyclerView.setAdapter(galleryRecyclerAdapter);
        } else {
            new PhotosTask("water", 100, 1, new PhotosTask.TaskListener() {
                @Override
                public void onComplete(Photos photos) {
                    mPhotos = photos;
                    galleryRecyclerAdapter = new GalleryRecyclerAdapter(getContext(), photos.getPhotos());
                    recyclerView.setAdapter(galleryRecyclerAdapter);
                }
            }).execute();
        }

        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_PHOTOS, mPhotos);
    }
}
