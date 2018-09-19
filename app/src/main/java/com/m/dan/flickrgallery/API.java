package com.m.dan.flickrgallery;

import com.m.dan.flickrgallery.models.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://api.flickr.com/services/rest/";
    String API_KEY = "949e98778755d1982f537d56236bbb42";


    @GET("?method=flickr.photos.search")
    Call<SearchResult> getPhotos(@Query("api_key") String api_key, @Query("text") String text,
                                 @Query("extras") String extras, @Query("per_page") int perPage,
                                 @Query("page") int page, @Query("format") String format,
                                 @Query("nojsoncallback") int callback);


}
