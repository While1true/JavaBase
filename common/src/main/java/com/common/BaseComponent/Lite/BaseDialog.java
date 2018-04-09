package com.common.BaseComponent.Lite;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.common.Util.AppContext;
import com.common.Util.InputUtils;
import com.master.rxlib.Rx.Utils.RxLifeUtils;

/**
 * Created by 不听话的好孩子 on 2018/4/9.
 */

public abstract class BaseDialog extends DialogFragment {
    protected View rootview;

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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog onCreateDialog = super.onCreateDialog(savedInstanceState);
        Window window = onCreateDialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        if (fullscrenn()) {
            window.requestFeature(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0xfffafafa));
            attributes.width = AppContext.get().getResources().getDisplayMetrics().widthPixels;
            attributes.gravity = Gravity.CENTER;
        }
        return super.onCreateDialog(savedInstanceState);
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
        RxLifeUtils.getInstance().remove(this);
    }

    protected abstract int getLayoutId();

    protected boolean fullscrenn() {
        return true;
    }

    public void show(FragmentManager manager) {
        super.show(manager, getClass().getSimpleName());
    }
}
