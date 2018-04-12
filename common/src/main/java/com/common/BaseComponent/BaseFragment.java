package com.common.BaseComponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.BaseComponent.Original.BaseF;
import com.common.BaseComponent.Original.IBaseView;
import com.common.Util.LogUtil;

/**
 * Created by 不听话的好孩子 on 2018/4/8.
 */

public abstract class BaseFragment extends BaseF implements IBaseView {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

    protected abstract void init(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();

    @Override
    public void Log(String message) {
        LogUtil.Log(getClass().getSimpleName(), message);
    }

}
