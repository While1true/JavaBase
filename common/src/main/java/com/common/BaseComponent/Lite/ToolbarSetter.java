package com.common.BaseComponent.Lite;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by 不听话的好孩子 on 2018/4/9.
 */


public class ToolbarSetter {
    private int navigationres;
    private Drawable navigation;
    private int logores;
    private Drawable logo;
    private CharSequence title;
    private int titleres;
    private CharSequence subtitle;
    private int subtitleres;
    private boolean iconvisable;
    private View.OnClickListener navigationOnClickListener;

    public ToolbarSetter setNavigationIcon(int drawres) {
        this.navigationres = drawres;
        return this;
    }

    public ToolbarSetter setNavigationIcon(Drawable navigation) {
        this.navigation = navigation;
        return this;
    }

    public ToolbarSetter setLogo(Drawable logo) {
        this.logo = logo;
        return this;
    }

    public ToolbarSetter setLogo(int logores) {
        this.logores = logores;
        return this;
    }

    public ToolbarSetter setTitle(CharSequence title) {
        this.title = title;
        return this;
    }

    public ToolbarSetter setTitle(int titleres) {
        this.titleres = titleres;
        return this;
    }

    public ToolbarSetter setsubTitle(CharSequence subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public ToolbarSetter setsubTitle(int subtitleres) {
        this.subtitleres = subtitleres;
        return this;
    }

    public ToolbarSetter setNavigationOnClickListener(View.OnClickListener navigationOnClickListener) {
        this.navigationOnClickListener = navigationOnClickListener;
        return this;
    }

    public ToolbarSetter setMenuIconVisable(boolean iconvisable) {
        this.iconvisable = iconvisable;
        return this;
    }

    @SuppressLint("RestrictedApi")
    public ToolbarSetter Setter(Toolbar toolbar) {
        if (logores != 0) {
            toolbar.setLogo(logores);
        }
        if (logo != null) {
            toolbar.setLogo(logo);
        }
        if (navigationres != 0) {
            toolbar.setNavigationIcon(navigationres);
        }
        if (navigation != null) {
            toolbar.setNavigationIcon(navigation);
        }
        if (titleres != 0) {
            toolbar.setTitle(titleres);
        }
        if (title != null) {
            toolbar.setTitle(title);
        }
        if (subtitleres != 0) {
            toolbar.setSubtitle(subtitleres);
        }
        if (subtitle != null) {
            toolbar.setSubtitle(subtitle);
        }
        if (iconvisable) {
            ((MenuBuilder) toolbar.getMenu()).setOptionalIconsVisible(iconvisable);
        }
        if (navigationOnClickListener != null) {
            toolbar.setNavigationOnClickListener(navigationOnClickListener);
        }
        return this;
    }
}