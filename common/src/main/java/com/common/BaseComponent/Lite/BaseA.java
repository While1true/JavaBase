package com.common.BaseComponent.Lite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.master.rxlib.Rx.Utils.RxLifeUtils;

import com.common.Util.ActivityUtils;

/**
 * Created by 不听话的好孩子 on 2018/4/8.
 */

public abstract class BaseA extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.remove(this);
        RxLifeUtils.getInstance().remove(this);
    }
}
