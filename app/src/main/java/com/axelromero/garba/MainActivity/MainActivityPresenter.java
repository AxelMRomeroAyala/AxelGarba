package com.axelromero.garba.MainActivity;

import android.content.Context;
import com.axelromero.garba.GarbaApiCallInterface;
import com.axelromero.garba.R;
import com.axelromero.garba.models.GarbaItemListModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPresenter {

    MainActivityInteractor interactor;
    GarbaItemListModel garbaItemListModel = new GarbaItemListModel();

    public MainActivityPresenter(MainActivityInteractor interactor) {
        this.interactor = interactor;
    }

    public void getProducts(Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GarbaApiCallInterface service = retrofit.create(GarbaApiCallInterface.class);

        Call<GarbaItemListModel> call = service.getProducts();

        call.enqueue(new Callback<GarbaItemListModel>() {
            @Override
            public void onResponse(Call<GarbaItemListModel> call, Response<GarbaItemListModel> response) {
                interactor.getProducts(response.body());
            }

            @Override
            public void onFailure(Call<GarbaItemListModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
