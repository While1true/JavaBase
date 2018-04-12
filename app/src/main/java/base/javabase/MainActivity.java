package base.javabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.common.BaseComponent.Imp.SearchActivity_;
import com.common.Net.RxManager;
import com.common.Util.GsonUtils;
import com.common.Util.LogUtil;
import com.master.rxlib.Rx.Base;
import com.master.rxlib.Rx.Net.RetrofitHttpManger;
import com.master.rxlib.Rx.RxSchedulers;
import com.nestrefreshlib.Adpater.Base.Holder;
import com.nestrefreshlib.Adpater.Base.ItemHolder;
import com.nestrefreshlib.Adpater.Impliment.BaseHolder;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class MainActivity extends SearchActivity_<HashMap<String,String>> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        RxManager.init(new RetrofitHttpManger.Builder().setBaseUrl("http://10.0.110.134:8090/masterWeiBo/").setShowlog(BuildConfig.showlog).Builder());
        super.onCreate(savedInstanceState);
        LogUtil.Log("你是谁？来自哪里");

    }

    @Override
    protected ItemHolder<HashMap<String,String>> getItemHolder() {
        return new BaseHolder<HashMap<String,String>>(R.layout.state_nomore) {
            @Override
            public void onViewBind(Holder holder, HashMap<String,String> s, int i) {
                holder.getView(R.id.tv_nomore).getLayoutParams().height= ViewGroup.LayoutParams.WRAP_CONTENT;
                holder.itemView.getLayoutParams().height= ViewGroup.LayoutParams.WRAP_CONTENT;
                holder.setText(R.id.tv_nomore, "这是第" + i + "项"+"\n"+ GsonUtils.map2String(s,String.class));
            }
        };
    }

    @Override
    protected Observable<Base<List<HashMap<String,String>>>> getObserver(int pagenum, int pagesize) {
        return RxManager.create(Api.class).getArtical("基本", pagenum, pagesize).compose(RxSchedulers.<Base<List<HashMap<String,String>>>>compose());
    }

    @Override
    protected int getEmptyLayout() {
        return R.layout.state_empty;
    }

    interface Api {
        @GET("articallist")
        Observable<Base<List<HashMap<String,String>>>> getArtical(@Query("category") String category, @Query("pagenum") int pagenum, @Query("pagesize") int pagesize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
