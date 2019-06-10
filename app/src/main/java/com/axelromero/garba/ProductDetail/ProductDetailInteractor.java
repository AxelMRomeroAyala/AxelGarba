package com.axelromero.garba.ProductDetail;

import com.axelromero.garba.models.GarbaItemDetailModel;
import com.axelromero.garba.models.GarbaItemReviewModel;

public interface ProductDetailInteractor {

    void onProductDetailLoaded(GarbaItemDetailModel model);
    void onProductReviewLoaded(GarbaItemReviewModel model);

}
