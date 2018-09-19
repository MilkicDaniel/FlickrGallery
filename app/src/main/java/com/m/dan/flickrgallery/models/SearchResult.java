package com.m.dan.flickrgallery.models;

import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("photos")
    private Photos photos;

    @SerializedName("stat")
    private String stat;

    public SearchResult(Photos photos, String stat) {
        this.photos = photos;
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "photos=" + photos +
                ", stat='" + stat + '\'' +
                '}';
    }

    public Photos getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
