package com.m.dan.flickrgallery.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.m.dan.flickrgallery.activities.MainActivity;
import com.m.dan.flickrgallery.R;
import com.m.dan.flickrgallery.adapteres.GalleryPagerAdapter;
import com.m.dan.flickrgallery.models.Photo;
import com.m.dan.flickrgallery.viewModel.GalleryViewModel;
import java.util.ArrayList;
import java.util.Objects;


public class PagerGalleryFragment extends Fragment {

    public static final String TAG = "PagerGalleryFragment";
    private GalleryViewModel model;
    private GalleryPagerAdapter galleryPagerAdapter;
    private SearchView searchView;

    public PagerGalleryFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pager_gallery, container, false);
        ViewPager mViewPager = root.findViewById(R.id.container);
        searchView = getActivity().findViewById(R.id.toolbar_search);
        AppBarLayout appBar = getActivity().findViewById(R.id.appbar);

        appBar.setExpanded(false, false);
        searchView.setEnabled(false);

        model = ViewModelProviders.of((MainActivity) Objects.requireNonNull(getContext())).get(GalleryViewModel.class);

        galleryPagerAdapter = new GalleryPagerAdapter(getChildFragmentManager(), model.getPhotoList().getValue());

        mViewPager.setAdapter(galleryPagerAdapter);
        mViewPager.setCurrentItem(model.getGalleryPosition().getValue());

        model.getPhotoList().observe(this, new Observer<ArrayList<Photo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Photo> photos) {
                galleryPagerAdapter.notifyDataSetChanged();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                model.setGalleryPosition(position);
                model.getMorePhotos(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchView.setEnabled(true);
    }
}
