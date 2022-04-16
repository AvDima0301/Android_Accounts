package com.example.example.Retrofit;

import static com.example.example.constants.Urls.BASE;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    //private static final String BASE_URL = "http://spu911.novakvova.com";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public UserAPI getJSONApi() {
        return mRetrofit.create(UserAPI.class);
    }
}
