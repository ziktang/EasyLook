package com.example.tctctc.easylook.ganhuojizhongying;


import android.content.Context;
import android.util.Log;

import com.example.tctctc.easylook.Utils.CacheUtils;
import com.example.tctctc.easylook.ganhuojizhongying.Model.DateLab;
import com.example.tctctc.easylook.ganhuojizhongying.Model.DayGanHuo;
import com.example.tctctc.easylook.ganhuojizhongying.Model.GanHuoClient;
import com.example.tctctc.easylook.ganhuojizhongying.Model.CateGanHuo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.R.attr.type;

/**
 * Created by tctctc on 2016/9/23.
 */

public class GanHuoSource implements GanHuoContract.GanHuoData {

    private GanHuoClient.GanHuoDataService mGanHuoDataService;
    private CacheUtils mCacheUtils;
    private Gson mGson;

    public GanHuoSource(Context context){
        mCacheUtils = mCacheUtils.getInstance(context);
        mGson = new Gson();
    }

    public Observable<CateGanHuo> getRandomList(String cate,int count){
        Observable<CateGanHuo> observable = getGanHuoDataService().getRandomData(cate,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public Observable<CateGanHuo> getCateList(String cate,int count,int page){
        Observable<CateGanHuo> observable = getGanHuoDataService().getCateData(cate,count,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public Observable<DayGanHuo> getDayList(){
        final GanHuoClient.GanHuoDataService ganHuoDataService = getGanHuoDataService();
        Observable<DateLab> observable = ganHuoDataService.getDateData();
        Observable<DayGanHuo> dayGanHuoObservable = observable.flatMap(new Func1<DateLab, Observable<ResponseBody>>() {
                    @Override
                    public Observable<ResponseBody> call(DateLab dateLab) {
                        return ganHuoDataService.getDayData(dateLab.getResults().get(0).replace("-", "/"));
                    }
                }).
                map(new Func1<ResponseBody, DayGanHuo>() {
                    @Override
                    public DayGanHuo call(ResponseBody response) { // 参数类型 String
                        DayGanHuo dayGanHuo;
                        try {
                            //将不确定的json数据格式转化
                            JSONObject jsonObject = new JSONObject(response.string());
                            dayGanHuo = new DayGanHuo();
                            Gson gson = new Gson();
                            List<String> categorys = gson.fromJson(jsonObject.getString("category"), new TypeToken<List<String>>() {
                            }.getType());
                            dayGanHuo.setCategory(categorys);
                            dayGanHuo.setError(jsonObject.getBoolean("error"));
                            JSONObject results = jsonObject.getJSONObject("results");
                            JSONArray strings = results.names();
                            List<CateGanHuo.CateBean> ganHuoBeanList = new ArrayList<CateGanHuo.CateBean>();
                            for (int i = 0; i < strings.length(); i++) {
                                List<CateGanHuo.CateBean> ganHuoBeans = gson.fromJson(results.getString((String) strings.get(i)), new TypeToken<List<CateGanHuo.CateBean>>() {
                                }.getType());
                                ganHuoBeanList.addAll(ganHuoBeans);
                            }
                            dayGanHuo.setResults(ganHuoBeanList);
                            return dayGanHuo;
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return dayGanHuoObservable;
    }


    public Observable<DateLab> getDateList(){
        Observable<DateLab> observable = getGanHuoDataService().getDateData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public List<CateGanHuo.CateBean> getCacheData(String key) {
        if (!mCacheUtils.isCacheEmpty(key)){
            String jsonString = mCacheUtils.getAsString(key);
            return  jsonString2Bean(jsonString).getResults();
        }
        return null;
    }

    @Override
    public void saveCacheData(String key,String data) {
        if (mCacheUtils.isNewResponse(key,data)&&data!=null){
            mCacheUtils.put(key,data);
        }
    }


    private CateGanHuo jsonString2Bean(String jsonString){
        CateGanHuo cateGanHuo = mGson.fromJson(jsonString,CateGanHuo.class);
        return cateGanHuo;
    }

    private GanHuoClient.GanHuoDataService getGanHuoDataService() {
        if (mGanHuoDataService == null){
            mGanHuoDataService = GanHuoClient.getRetrofit().create(GanHuoClient.GanHuoDataService.class);
        }
        return mGanHuoDataService;
    }
}

