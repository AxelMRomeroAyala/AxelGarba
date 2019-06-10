package com.axelromero.garba.MainActivity;

import android.app.Application;
import android.content.Context;
import com.axelromero.garba.AxelGarbaApplication;
import com.axelromero.garba.GarbaApiCallInterface;
import com.axelromero.garba.models.GarbaItemListModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.inject.Inject;

public class MainActivityPresenter {

    MainActivityInteractor interactor;

    @Inject
    Retrofit retrofitService;

    public MainActivityPresenter(MainActivityInteractor interactor, Application application) {
        this.interactor = interactor;
        ((AxelGarbaApplication) application).getDataComponent().inject(this);
    }

    public void getProducts(Context context) {

        GarbaApiCallInterface service = retrofitService.create(GarbaApiCallInterface.class);

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
