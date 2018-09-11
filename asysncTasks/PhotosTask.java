package com.m.dan.flickrgallery.asysncTasks;

import android.os.AsyncTask;
import com.m.dan.flickrgallery.API;
import com.m.dan.flickrgallery.models.Photos;
import com.m.dan.flickrgallery.models.SearchResult;
import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosTask extends AsyncTask<Void, Void, SearchResult> {


    public interface TaskListener {
        void onComplete(Photos photos);
    }

    private String text;
    private int page;
    private int perPage;
    private TaskListener taskListener;

    public PhotosTask(String text, int perPage, int page, TaskListener taskListener) {
        this.text = text;
        this.page = page;
        this.perPage = perPage;
        this.taskListener = taskListener;
    }

    @Override
    protected SearchResult doInBackground(Void... voids) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        try {
            return api.getPhotos(API.API_KEY, text,"url_o, url_s", perPage, page, "json", 1).execute().body();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(SearchResult searchResult) {
        super.onPostExecute(searchResult);
        taskListener.onComplete(searchResult.getPhotos());
    }
}
