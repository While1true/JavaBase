package com.common.BaseComponent.Imp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.common.BaseComponent.BaseActivity;
import com.common.R;
import com.common.Widgets.PagerLoading.RefreshLayoutPageLoading;
import com.master.rxlib.Rx.Base;
import com.nestrefreshlib.Adpater.Base.ItemHolder;
import com.nestrefreshlib.RefreshViews.RefreshLayout;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 不听话的好孩子 on 2018/4/9.
 */

public abstract class ListActivity_<T> extends BaseActivity {
    @Override
    protected void init(Bundle savedInstanceState) {
        final RefreshLayoutPageLoading pageLoading = new RefreshLayoutPageLoading<T>((RefreshLayout) findViewById(R.id.refreshlayout), new LinearLayoutManager(this), isInnerAdapter()) {
            @Override
            public Observable<Base<List<T>>> getObservable() {
                return getObserver(getPagenum(), getPagesize());
            }
        }
                .addType(getItemHolder()).AddLifeOwner(this);
    }

    private boolean isInnerAdapter() {
        return true;
    }

    protected abstract ItemHolder<T> getItemHolder();


    protected abstract Observable<Base<List<T>>> getObserver(int pagenum, int pagesize);

    @Override
    protected int getLayoutID() {
        return R.layout.refreshlayout;
    }
}
