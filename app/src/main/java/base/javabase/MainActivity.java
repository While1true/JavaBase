package base.javabase;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.common.BaseComponent.BaseActivity;
import com.common.BaseComponent.Lite.ToolbarSetter;
import com.common.Util.LogUtil;


public class MainActivity extends BaseActivity {

    @Override
    protected void init(Bundle savedInstanceState) {
        new ToolbarSetter().setMenuIconVisable(true).Setter(toobar);
        LogUtil.Log("你是谁？来自哪里");
        setCenterTitle("主页");
    }

    @Override
    protected int getLayoutID() {
        return R.layout.state_nomore;
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
