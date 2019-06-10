package com.axelromero.garba.ImageCarrousel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.axelromero.garba.R;
import com.axelromero.garba.models.GarbaItemDetailModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ImageCarrouselActivity extends FragmentActivity {

    public final static String IMAGE_LIST = "image_list";
    ViewPager viewPager;
    List<GarbaItemDetailModel.GarbaImage> images;
    ImagePagerAdapter imagePagerAdapter;

    public static Intent getCallingIntent(Context context, List<GarbaItemDetailModel.GarbaImage> imageList) {
        Intent intent = new Intent(context, ImageCarrouselActivity.class);
        intent.putExtra(IMAGE_LIST, new Gson().toJson(imageList));

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_carrousel);
        viewPager = findViewById(R.id.iamge_carrousel_pager);

        images = new Gson().fromJson(getIntent().getStringExtra(IMAGE_LIST), new TypeToken<List<GarbaItemDetailModel.GarbaImage>>() {
        }.getType());

        imagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager(), images);
        viewPager.setAdapter(imagePagerAdapter);
    }

}

class ImagePagerAdapter extends FragmentStatePagerAdapter {

    List<GarbaItemDetailModel.GarbaImage> imageList;

    public ImagePagerAdapter(FragmentManager fm, List<GarbaItemDetailModel.GarbaImage> imageList) {
        super(fm);
        this.imageList = imageList;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(imageList.get(position).url);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }
}


