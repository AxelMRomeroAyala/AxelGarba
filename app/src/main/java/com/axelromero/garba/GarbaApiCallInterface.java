package com.axelromero.garba;


import com.axelromero.garba.models.GarbaItemDetailModel;
import com.axelromero.garba.models.GarbaItemListModel;
import com.axelromero.garba.models.GarbaItemReviewModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GarbaApiCallInterface {

    @GET("/products/")
    Call<GarbaItemListModel> getProducts();

    @GET("/products/{id}")
    Call<GarbaItemDetailModel> getProductDetail(@Path("id") String id);

    @GET("/products/{id}/reviews")
    Call<GarbaItemReviewModel> getProductReview(@Path("id") String id);
}
