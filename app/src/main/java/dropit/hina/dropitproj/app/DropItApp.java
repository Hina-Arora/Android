package dropit.hina.dropitproj.app;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.io.IOException;

import dropit.hina.dropitproj.BuildConfig;
import dropit.hina.dropitproj.endpoint.Endpoints;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DropItApp extends MultiDexApplication  {

    private Endpoints endPoints;

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public static void setInstance(DropItApp instance) {
        DropItApp.instance = instance;
    }

    private Boolean hasMore = true;
    public Endpoints getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(Endpoints endPoints) {
        this.endPoints = endPoints;
    }

    private static DropItApp instance;

    public static DropItApp getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        instance = this;
        initRetrofit();
    }


    private void initRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder request = original.newBuilder();
                request.header("Content-Type", "application/x-www-form-urlencoded");
                request.header("Accept", "application/json");
//                request.header("Authorization", "Bearer " + token);

                Request request1 = request.build();
                return chain.proceed(request1);
            }
        });
        httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS);
        httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        endPoints = retrofit.create(Endpoints.class);
    }
}

