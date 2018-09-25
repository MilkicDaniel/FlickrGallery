package com.m.dan.flickrgallery.adapteres;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.m.dan.flickrgallery.fragments.ImageFragment;
import com.m.dan.flickrgallery.models.Photo;
import java.util.ArrayList;


public class GalleryPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Photo> photos;

    public GalleryPagerAdapter(FragmentManager fm, ArrayList<Photo> photos) {
        super(fm);
        this.photos = photos;
    }

    @Override
    public Fragment getItem(int position) {

        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ImageFragment.BUNDLE_PHOTO, photos.get(position));
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

}
