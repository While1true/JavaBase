package com.common;

import android.app.Application;
import android.content.Context;

import com.common.Util.AppContext;
import com.common.Util.LogUtil;
import com.nestrefreshlib.RefreshViews.RefreshLayout;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by 不听话的好孩子 on 2018/4/8.
 */

public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppContext.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RefreshLayout.init(new RefreshLayout.DefaultBuilder()
                .setCanheaderDefault(true)
                .setCanfootrDefault(true)
                .setScrollLayoutIdDefault(R.layout.recyclerview)
                .setHeaderLayoutidDefault(R.layout.header_layout)
                .setFooterLayoutidDefault(R.layout.footer_layout));
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtil.Log(throwable.getMessage());
            }
        });
    }
}
