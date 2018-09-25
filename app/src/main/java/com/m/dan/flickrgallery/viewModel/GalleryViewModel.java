package com.m.dan.flickrgallery.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import com.m.dan.flickrgallery.activities.MainActivity;
import com.m.dan.flickrgallery.R;
import com.m.dan.flickrgallery.asysncTasks.PhotosTask;
import com.m.dan.flickrgallery.fragments.PagerGalleryFragment;
import com.m.dan.flickrgallery.models.Photo;
import com.m.dan.flickrgallery.models.Photos;
import java.util.ArrayList;
import java.util.Objects;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Photo>> photoList = new MutableLiveData<>();
    private MutableLiveData<Integer> galleryPosition = new MutableLiveData<>();
    private Photos photos;
    private String search;
    private int pageNumber;
    private int perPageResults;
    private int oldSize = 0;


    public GalleryViewModel() {
        photoList.setValue(new ArrayList<Photo>());
        galleryPosition.setValue(0);
    }



    public void openFullscreenPhoto(Context context, int position) {

        ((MainActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new PagerGalleryFragment(), PagerGalleryFragment.TAG)
                .addToBackStack(null)
                .commit();

        galleryPosition.setValue(position);
    }

    public void setGalleryPosition(int position){
        galleryPosition.setValue(position);
    }

    public MutableLiveData<Integer> getGalleryPosition() {
        return galleryPosition;
    }

    public void getMorePhotos(int position){

        if(oldSize != Objects.requireNonNull(photoList.getValue()).size()) {
            oldSize = photoList.getValue().size();
        }

        if(position != oldSize-1){
            return;
        }

        if(search == null || search.equals(""))
            return;

        pageNumber++;

        if(photos.getPages() < pageNumber)
            return;

        new PhotosTask(search, perPageResults, pageNumber, new PhotosTask.TaskListener() {
            @Override
            public void onComplete(Photos tmpPhotos) {
                photos = tmpPhotos;
                photoList.getValue().addAll(tmpPhotos.getPhotos());
                photoList.setValue( photoList.getValue());

            }
        }).execute();
    }

    public void searchFlickrForPhotos(String search, int pageNumber, int perPageResults){

        this.search = search;
        this.pageNumber = pageNumber;
        this.perPageResults = perPageResults;

        galleryPosition.setValue(0);
        oldSize = 0;

        Objects.requireNonNull(photoList.getValue()).clear();
        photoList.setValue(photoList.getValue());

        new PhotosTask(search, perPageResults, pageNumber, new PhotosTask.TaskListener() {
            @Override
            public void onComplete(Photos tmpPhotos) {
                photos = tmpPhotos;
                photoList.getValue().addAll(tmpPhotos.getPhotos());
                photoList.setValue(photoList.getValue());

            }
        }).execute();
    }

    public Photos getPhotos() {
        return photos;
    }

    public LiveData<ArrayList<Photo>> getPhotoList() {
        return photoList;
    }

}
