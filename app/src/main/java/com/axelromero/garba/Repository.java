package com.axelromero.garba;

import com.axelromero.garba.models.GarbaItemDetailModel;
import com.axelromero.garba.models.GarbaItemListModel;
import com.axelromero.garba.models.GarbaItemReviewModel;
import retrofit2.Call;

public class Repository {

    private GarbaApiCallInterface apiCallInterface;

    public Repository(GarbaApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    Call<GarbaItemListModel> getProducts() {
        return apiCallInterface.getProducts();
    }

    public Call<GarbaItemDetailModel> getProductDetail(String id) {
        return apiCallInterface.getProductDetail(id);
    }

    public Call<GarbaItemReviewModel> getProductReviews(String id) {
        return apiCallInterface.getProductReview(id);
    }
}
