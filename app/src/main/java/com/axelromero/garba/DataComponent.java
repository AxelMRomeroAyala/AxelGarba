package com.axelromero.garba;

import com.axelromero.garba.MainActivity.MainActivityPresenter;
import com.axelromero.garba.ProductDetail.ProductDetailPresenter;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules={DataModule.class})

public interface DataComponent {
    void inject(AxelGarbaApplication axelGarbaApplication);

    void inject(ProductDetailPresenter productDetailPresenter);

    void inject(MainActivityPresenter mainActivityPresenter);
}
