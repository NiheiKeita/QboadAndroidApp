package com.iggyapp.qboad.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.iggyapp.qboad.BuildConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {
//    public static QBoadRestInterface getRxQBoadRestInterface(String url){
//        Interceptor
//    }

    public static QBoadRestInterface getRxQBoadRestInterface(String url) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String versionName = BuildConfig.VERSION_NAME;
                String userAgentName = "TalkWave "+versionName;
                Request request =chain.request().newBuilder()
                        .header("User-Agent", userAgentName)
                        .build();
                return chain.proceed(request);
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Define.API_ACCESS_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Define.API_ACCESS_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Define.API_ACCESS_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(QBoadRestInterface.class);
    }
    public static String getApiAccessToken(@NotNull Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Define.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String sApiAccessToken =  prefs.getString("api_access_token", "");
//        Logger.methodStart(TAG);
//        Logger.i(TAG,sApiAccessToken);
        return sApiAccessToken;
    }
    public static void setApiAccessToken(String sApiAccessToken , @NotNull Context context) {
//        Logger.methodStart(TAG);
//        Logger.i(TAG,sApiAccessToken);

        SharedPreferences prefs = context.getSharedPreferences(Define.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("api_access_token", sApiAccessToken);
        editor.apply();
    }
}
