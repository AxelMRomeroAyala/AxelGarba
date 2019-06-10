package com.axelromero.garba.ImageCarrousel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.axelromero.garba.R;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {

    public static final String IMAGE_URL = "image_url";
    private String imageUrl;

    public static ImageFragment newInstance(String url) {
        ImageFragment myFragment = new ImageFragment();

        Bundle args = new Bundle();
        args.putString(IMAGE_URL, url);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUrl = getArguments().getString(IMAGE_URL, "");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.image_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Picasso.get().load("http:" + imageUrl).into((ImageView) getView());
    }
}
