package com.common.BaseComponent.Lite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.Util.InputUtils;
import com.master.rxlib.Rx.Utils.RxLifeUtils;

/**
 * Created by 不听话的好孩子 on 2018/4/9.
 */

public abstract class BaseSheetDialog extends BottomSheetDialogFragment {
    protected View rootview;
    private BottomSheetBehavior<View> behavior;

    @Override
    public void dismiss() {
        try {
            InputUtils.hideKeyboard(getDialog());
        } catch (Exception e) {
        }
        super.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(getLayoutId(), container, true);
        return rootview;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

    protected abstract void init(View view, Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        behavior.setBottomSheetCallback(null);
        RxLifeUtils.getInstance().remove(this);
    }

    protected abstract int getLayoutId();

    @Override
    public void onResume() {
        super.onResume();
        behavior = BottomSheetBehavior.from(rootview.getRootView().findViewById(android.support.design.R.id.design_bottom_sheet));
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    public void show(FragmentManager manager) {
        show(manager, getClass().getSimpleName());
    }
}
