package com.axelromero.garba;

import android.app.Application;

public class AxelGarbaApplication extends Application {

    private static AxelGarbaApplication app;
    DataComponent dataComponent;

    public static AxelGarbaApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        initDataComponent();

        dataComponent.inject(this);
    }

    private void initDataComponent(){
        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule(this))
                .build();
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}
