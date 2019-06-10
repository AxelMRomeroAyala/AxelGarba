package com.axelromero.garba;

import android.app.Application;
import android.app.Service;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public class DataModule {

    Application application;

    public DataModule(Application application) {
        this.application = application;
    }

    @Provides
    public Service provideItemService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com")
                .client(okHttpClient)
                //converts Retrofit response into Observable
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }
}
