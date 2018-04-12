package com.common.BaseComponent.Original;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.master.rxlib.Rx.Utils.RxLifeUtils;

/**
 * Created by 不听话的好孩子 on 2018/4/8.
 */

public abstract class BaseF extends Fragment {
    private boolean isVisibleToUser, isfirst = true, isViewCreated;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        if (isVisibleToUser && isfirst) {
            isfirst = false;
            loadLazy();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isfirst && isVisibleToUser && isViewCreated) {
            isfirst = false;
            loadLazy();
        }
    }

    public void loadLazy() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxLifeUtils.getInstance().remove(this);
    }
}
