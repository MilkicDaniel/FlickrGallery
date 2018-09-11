package com.m.dan.flickrgallery.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("owner")
    private String owner;
    @SerializedName("title")
    private String title;
    @SerializedName("url_o")
    private String url;
    @SerializedName("url_s")
    private String thumbnailUrl;
    @SerializedName("height_o")
    private String height;
    @SerializedName("width_o")
    private String width;

    public Photo(String id, String owner, String title, String url, String thumbnailUrl, String height, String width) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.owner);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.height);
        dest.writeString(this.width);
    }

    protected Photo(Parcel in) {
        this.id = in.readString();
        this.owner = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.thumbnailUrl = in.readString();
        this.height = in.readString();
        this.width = in.readString();
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
