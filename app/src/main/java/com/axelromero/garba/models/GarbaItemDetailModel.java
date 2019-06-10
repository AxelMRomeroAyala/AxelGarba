package com.axelromero.garba.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GarbaItemDetailModel {

    public String xid;
    public String description;
    public String summary;
    public String brand;
    @SerializedName("original_brand")
    public String originalBrand;
    @SerializedName("list_price")
    public int listPrice;
    public int price;
    public int discount;
    @SerializedName("main_image")
    public GarbaImage mainImage;
    public Resources resources;

    public class GarbaImage {
        @SerializedName("max_width")
        public int maxWidth;
        public String url;
    }

    public class Resources {
        public List<GarbaImage> images;
    }
}
