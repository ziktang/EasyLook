/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tctctc.easylook.ganhuojizhongying;

import android.content.Context;

import com.example.tctctc.easylook.Base.BaseModel;
import com.example.tctctc.easylook.Base.BasePresenter;
import com.example.tctctc.easylook.Base.BaseView;
import com.example.tctctc.easylook.ganhuojizhongying.Model.CateGanHuo;
import com.example.tctctc.easylook.ganhuojizhongying.Model.DateLab;
import com.example.tctctc.easylook.ganhuojizhongying.Model.DayGanHuo;

import java.util.List;

import rx.Observable;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface GanHuoContract {

    interface View extends BaseView<Presenter> {

        void showMoreCate(List<CateGanHuo.CateBean> ganHuoBeanList);

        void show(List<CateGanHuo.CateBean> ganHuoBeanList);

        void stopRefresh();

        void startRefresh();

        void NetError();

    }

    interface Presenter extends BasePresenter{
        void loadMore();

        void RefreshData();

        void initData();

        void GoToDetail(android.view.View view, Context context, CateGanHuo.CateBean cateBean);
    }

    interface GanHuoData extends BaseModel{
        public Observable<CateGanHuo> getRandomList(String cate, int count);
        public Observable<CateGanHuo> getCateList(String cate, int count, int page);
        public Observable<DayGanHuo> getDayList();
        public Observable<DateLab> getDateList();
        public List<CateGanHuo.CateBean> getCacheData(String key);
        public void saveCacheData(String key,String data);

    }
}
