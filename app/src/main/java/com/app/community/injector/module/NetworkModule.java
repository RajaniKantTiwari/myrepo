package com.app.community.injector.module;


import com.app.community.BuildConfig;
import com.app.community.injector.JsonExclusionStrategy;
import com.app.community.injector.scope.PerApplication;
import com.app.community.network.Repository;
import com.app.community.network.RetrofitRepository;
import com.app.community.utils.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 */
@Module
public class NetworkModule {

    public static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";

    @Provides
    @PerApplication
    Repository provideRepository(Retrofit retrofit) {
        return new RetrofitRepository(retrofit);
    }

    @Provides
    @PerApplication
    Retrofit provideRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat(API_DATE_FORMAT)
                .addSerializationExclusionStrategy(new JsonExclusionStrategy())
                .addDeserializationExclusionStrategy(new JsonExclusionStrategy())
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            //String accessToken = Hawk.get(PreferenceConstants.TOKEN);
            String accessToken ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJycnJyQGdtYWlsLmNvbSIsImF1ZGllbmNlIjoiSU9TIiwiU1RBVFVTIjoiQUNUSVZFIiwicGFzcyI6IiQyYSQxMCRmWVB3emtIVGl1RFVla2h1VmJqbjYud2lpY2htcXNLWExUckswNXFycmszc1I4MWN0eEo5RyIsImNyZWF0ZWQiOjE1MDk2MDQ3MjkyNzAsImV4cCI6MTUxMDIwOTUyOSwiU0lOR0xFX1VTRVJfTE9HSU4iOjgxN30.CO3NngRfIE2ivyYQXKZpcbqdQlFz2wMStVDOEmE0zCx4rDLjOrlwWQaPNUZgcUxeEAZzqY6EsjHowmqwTMZ5vA";
            if(accessToken!=null){
                Request.Builder requestBuilder = original.newBuilder()
                        .header(ApiConstants.AUTHORIZATION, accessToken)
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            } else {
                return chain.proceed(original);
            }
        });
        httpClient.addInterceptor(logging);
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

}
