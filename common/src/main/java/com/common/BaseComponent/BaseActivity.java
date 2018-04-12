package com.common.BaseComponent;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.BaseComponent.Original.BaseA;
import com.common.BaseComponent.Original.IBaseView;
import com.common.BaseComponent.Original.ToolbarSetter;
import com.common.R;
import com.common.Util.LogUtil;

/**
 * Created by 不听话的好孩子 on 2018/4/8.
 */

public abstract class BaseActivity extends BaseA implements IBaseView{

    protected Toolbar toobar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (showToolbar()) {
            setContentView(R.layout.toolbar_layout);
            toobar = findViewById(R.id.toolbar);
            LinearLayout linearLayout = findViewById(R.id.myroot);
            toobar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            setSupportActionBar(toobar);
            setToobarAttrs();
            if (getLayoutID() != 0) {
                getLayoutInflater().inflate(getLayoutID(), linearLayout, true);
            }
        }else {
            if (getLayoutID() != 0) {
                setContentView(getLayoutID());
            }
        }
        init(savedInstanceState);
    }

    protected void setCenterTitle(CharSequence title) {
        TextView textView = toobar.findViewById(R.id.centerText);
        textView.setText(title);
        setTitle("");
    }

    protected void setToobarAttrs() {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_back);
        new ToolbarSetter().setNavigationIcon(drawable)
                .setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).Setter(toobar);
    }

    protected abstract void init(Bundle savedInstanceState);

    protected boolean showToolbar() {
        return true;
    }

    protected abstract int getLayoutID();

    @Override
    public void Log(String message) {
        LogUtil.Log(getClass().getSimpleName(),message);
    }

}
