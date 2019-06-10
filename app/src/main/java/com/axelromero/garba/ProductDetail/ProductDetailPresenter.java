package com.axelromero.garba.ProductDetail;

import android.app.Application;
import android.content.Context;
import com.axelromero.garba.AxelGarbaApplication;
import com.axelromero.garba.GarbaApiCallInterface;
import com.axelromero.garba.models.GarbaItemDetailModel;
import com.axelromero.garba.models.GarbaItemReviewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.inject.Inject;

public class ProductDetailPresenter {

    ProductDetailInteractor productDetailInteractor;

    @Inject
    Retrofit retrofitService;

    ProductDetailPresenter(ProductDetailInteractor interactor, Application application) {
        this.productDetailInteractor = interactor;
        ((AxelGarbaApplication) application).getDataComponent().inject(this);
    }

    public void loadProductDetail(Context context, String id) {

        GarbaApiCallInterface service = retrofitService.create(GarbaApiCallInterface.class);

        Call<GarbaItemDetailModel> call = service.getProductDetail(id);

        call.enqueue(new Callback<GarbaItemDetailModel>() {
            @Override
            public void onResponse(Call<GarbaItemDetailModel> call, Response<GarbaItemDetailModel> response) {
                productDetailInteractor.onProductDetailLoaded(response.body());
            }

            @Override
            public void onFailure(Call<GarbaItemDetailModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void loadProductReviews(Context context, String id) {

        GarbaApiCallInterface service = retrofitService.create(GarbaApiCallInterface.class);

        Call<GarbaItemReviewModel> call = service.getProductReview(id);

        call.enqueue(new Callback<GarbaItemReviewModel>() {
            @Override
            public void onResponse(Call<GarbaItemReviewModel> call, Response<GarbaItemReviewModel> response) {
                productDetailInteractor.onProductReviewLoaded(response.body());
            }

            @Override
            public void onFailure(Call<GarbaItemReviewModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
