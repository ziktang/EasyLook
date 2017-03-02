package com.example.tctctc.easylook.ganhuojizhongying.Model;



import com.example.tctctc.easylook.Config.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tctctc on 2016/9/23.
 */

public class GanHuoClient {

    static Retrofit mMetrofit;

    public static Retrofit getRetrofit(){
        if (mMetrofit==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();
            mMetrofit = new Retrofit.Builder()
                    .baseUrl(API.GhApi)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mMetrofit;
    }

    public interface GanHuoDataService {
        //http://gank.io/api/history/content/day/2016/05/11
        @GET("day/{dateString}")
        Observable<ResponseBody> getDayData(@Path("dateString") String dateString);

        //http://gank.io/api/day/history
        @GET("day/history")
        Observable<DateLab> getDateData();

        //http://gank.io/api/data/Android/10/1
        @GET("data/{cate}/{count}/{page}")
        Observable<CateGanHuo> getCateData(@Path("cate") String cate,@Path("count") int count,@Path("page") int page);

        //http://gank.io/api/random/data/Android/20
        @GET("random/data/{cate}/{count}")
        Observable<CateGanHuo> getRandomData(@Path("cate") String cate,@Path("count") int count);
    }
}
