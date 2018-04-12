package com.common.BaseComponent.Imp;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.common.BaseComponent.BaseActivity;
import com.common.R;
import com.common.Util.InputUtils;
import com.common.Util.SizeUtils;
import com.common.Util.StateBarUtils;
import com.common.Widgets.PagerLoading.RefreshLayoutPageLoading;
import com.master.rxlib.Rx.Base;
import com.master.rxlib.Rx.MyObserver;
import com.master.rxlib.Rx.Utils.EditTextWatcher;
import com.nestrefreshlib.Adpater.Base.ItemHolder;
import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.State.Interface.StateEnum;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;

/**
 * Created by 不听话的好孩子 on 2018/4/9.
 */

public abstract class SearchActivity_<T> extends BaseActivity {

    protected EditText editText;

    protected String searchtext;

    @Override
    protected void init(Bundle savedInstanceState) {
        if (getLayoutID() == 0)
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        else
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        StateBarUtils.performTransStateBar(getWindow());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode());
        }
        editText = findViewById(R.id.et_input);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputUtils.hideKeyboard(editText);
                onBackPressed();
            }
        });
        handleTitlebar();
        initData();
    }

    private void initData() {
        final RefreshLayoutPageLoading pageLoading = new RefreshLayoutPageLoading<T>((RefreshLayout) findViewById(R.id.refreshlayout), new LinearLayoutManager(this), isInnerAdapter()) {
            @Override
            public Observable<Base<List<T>>> getObservable() {
                return getObserver(getPagenum(), getPagesize());
            }
        }
                .addType(getItemHolder()).AddLifeOwner(this);
        if (getEmptyLayout() != 0) {
            pageLoading.getStateAdapter().setLayoutId(StateEnum.SHOW_EMPTY, getEmptyLayout());
            pageLoading.getStateAdapter().showEmpty();
        } else {
            pageLoading.Go();
        }

        Observable.create(new EditTextWatcher(editText))
                .debounce(600, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        boolean empty = TextUtils.isEmpty(s);
                        if (empty) {
                            pageLoading.getStateAdapter().showEmpty();
                        }
                        return !empty;
                    }
                }).subscribe(new MyObserver<String>(this) {
            @Override
            public void onNext(String s) {
                super.onNext(s);
                searchtext = s;
                pageLoading.RestAndGo();
            }
        });
    }

    protected void handleTitlebar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CardView cardview = findViewById(R.id.cardview);
            cardview.setMaxCardElevation(0f);
            cardview.setContentPadding(0, 0, 0, SizeUtils.dp2px(6f));
        }
    }

    @Override
    protected final boolean showToolbar() {
        return false;
    }

    @Override
    protected final int getLayoutID() {
        return R.layout.search_activity;
    }

    protected boolean isInnerAdapter() {
        return true;
    }

    protected abstract ItemHolder<T> getItemHolder();

    protected abstract Observable<Base<List<T>>> getObserver(int pagenum, int pagesize);

    protected abstract int getEmptyLayout();
}
