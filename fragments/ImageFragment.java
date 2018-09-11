package com.m.dan.flickrgallery.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.m.dan.flickrgallery.R;
import com.m.dan.flickrgallery.models.Photo;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {

    public static final String TAG = "ImageFragment";
    public static final String BUNDLE_PHOTO = "BUNDLE_PHOTO";

    public ImageFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView image = root.findViewById(R.id.image_image_view);
        TextView info = root.findViewById(R.id.image_info);
        TextView title = root.findViewById(R.id.image_title);

        Bundle bundle = getArguments();
        Photo photo = bundle.getParcelable(BUNDLE_PHOTO);

        if (photo.getUrl() != null) {

            title.setText(photo.getTitle());
            info.setText(getString(R.string.width_height, photo.getWidth(), photo.getHeight()));

            Picasso.get().load(photo.getUrl()).fit().centerInside()
                    .into(image);
        } else {
            // in case flickr is missing the original image for some reason.
            title.setText(photo.getTitle());
            info.setText(getString(R.string.width_height, "Unknown", "Unknown"));

            Picasso.get().load(photo.getThumbnailUrl()).fit().centerInside()
                    .into(image);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return root;
    }

}
