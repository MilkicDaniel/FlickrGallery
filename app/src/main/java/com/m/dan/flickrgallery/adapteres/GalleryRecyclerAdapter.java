package com.m.dan.flickrgallery.adapteres;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.m.dan.flickrgallery.MainActivity;
import com.m.dan.flickrgallery.R;
import com.m.dan.flickrgallery.fragments.ImageFragment;
import com.m.dan.flickrgallery.models.Photo;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Photo> photos;

    public GalleryRecyclerAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Photo photo = photos.get(position);

        Picasso.get().load(photo.getThumbnailUrl()).fit().centerCrop()
                .into(viewHolder.image);
        viewHolder.info.setText(photo.getTitle());

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageFragment imageFragment = new ImageFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(ImageFragment.BUNDLE_PHOTO, photo);
                imageFragment.setArguments(bundle);

                ((MainActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.overlay_container, imageFragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView info;

        private ViewHolder(View rootView) {
            super(rootView);

            image = rootView.findViewById(R.id.gallery_item_image);
            info = rootView.findViewById(R.id.gallery_item_info);


        }
    }



}
