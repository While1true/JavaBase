package com.common.Util;

import android.app.Application;

/**
 * Created by 不听话的好孩子 on 2018/4/9.
 */

public class AppContext {
    private static Application application;

    public static void init(Application applicationx) {
        application = applicationx;
    }

    public static <T extends Application> T get() {
        return (T) application;
    }
}
