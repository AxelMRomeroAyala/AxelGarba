package com.axelromero.garba.MainActivity;

import android.view.View;
import com.axelromero.garba.models.GarbaItemListModel;

public interface MainActivityInteractor{

    void getProducts(GarbaItemListModel itemListModel);

    void onProductSelected(View sharedView, String productId, String productImage);
}