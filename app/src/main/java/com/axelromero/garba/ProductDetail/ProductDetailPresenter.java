package com.axelromero.garba.ProductDetail;

import android.content.Context;
import com.axelromero.garba.GarbaApiCallInterface;
import com.axelromero.garba.R;
import com.axelromero.garba.models.GarbaItemDetailModel;
import com.axelromero.garba.models.GarbaItemReviewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailPresenter {

    ProductDetailInteractor productDetailInteractor;

    ProductDetailPresenter(ProductDetailInteractor interactor) {
        this.productDetailInteractor = interactor;
    }

    public void loadProductDetail(Context context, String id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GarbaApiCallInterface service = retrofit.create(GarbaApiCallInterface.class);

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GarbaApiCallInterface service = retrofit.create(GarbaApiCallInterface.class);

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
