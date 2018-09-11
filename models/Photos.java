package com.m.dan.flickrgallery.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Photos implements Parcelable {

    @SerializedName("page")
    private int page = 0;
    @SerializedName("pages")
    private int pages = 0;
    @SerializedName("perpage")
    private int perPage = 0;
    @SerializedName("total")
    private int total = 0;
    @SerializedName("photo")
    private ArrayList<Photo> photos = new ArrayList<>();

    @Override
    public String toString() {
        return "Photos{" +
                "page=" + page +
                ", pages=" + pages +
                ", perPage=" + perPage +
                ", total=" + total +
                ", photos=" + photos +
                '}';
    }

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.pages);
        dest.writeInt(this.perPage);
        dest.writeInt(this.total);
        dest.writeTypedList(this.photos);
    }

    protected Photos(Parcel in) {
        this.page = in.readInt();
        this.pages = in.readInt();
        this.perPage = in.readInt();
        this.total = in.readInt();
        this.photos = in.createTypedArrayList(Photo.CREATOR);
    }

    public static final Parcelable.Creator<Photos> CREATOR = new Parcelable.Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel source) {
            return new Photos(source);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };
}
