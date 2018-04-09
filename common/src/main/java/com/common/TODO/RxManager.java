package com.common.TODO;

import com.master.rxlib.Rx.Net.RetrofitHttpManger;

import okhttp3.OkHttpClient;

/**
 * Created by 不听话的好孩子 on 2018/4/2.
 */

public class RxManager {
    private static RetrofitHttpManger manger;
    public static <T> T create(Class<T> service) {
        return manger.get().create(service);
    }

    public static void init(){
        //TODO
        manger=null;
    }
    public static RetrofitHttpManger get() {
        return manger;
    }

    public static OkHttpClient getClient(){
        return manger.getClient();
    }
}
