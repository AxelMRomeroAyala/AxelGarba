package com.axelromero.garba.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GarbaItemListModel {

    public List<GarbaItem> items;

    public class GarbaItem{
        public String id;
        public String description;
        @SerializedName("image_url")
        public String imageUrl;
        public int price= 0;
        @SerializedName("list_price")
        public int listPrice= 0;
        public int discount= 0;
    }
}
