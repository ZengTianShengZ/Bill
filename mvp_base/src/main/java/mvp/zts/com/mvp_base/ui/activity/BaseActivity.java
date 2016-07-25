package mvp.zts.com.mvp_base.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_base.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    /**
     * the presenter of this Activity
     */
    protected P mPresenter;
    protected Context mContext;
    /**
     *  get  Toolbar
     */
    protected abstract Toolbar getToolbar();

    /**
     * get  Layout
     */
    protected abstract int getLayout();
    /**
     * init  Presenter
     */
    protected abstract void initPresenter();
    protected abstract void intiData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        setContentView(getLayout());
        ButterKnife.bind(this);
        intiData();
        initToolBar();
        initPresenter();
        checkPresenterIsNull();

    }


    /**
     * set the id of menu
     * @return if values is less then zero ,and the activity will not show menu
     */
    protected int getMenuRes(){
        return -1;
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(getMenuRes()<0)return true;
        getMenuInflater().inflate(getMenuRes(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //don't use finish() and use onBackPressed() will be a good practice , trust me !
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPresenterIsNull(){
        if(mPresenter == null){
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    final private void initToolBar() {
        if(getToolbar() == null){
            throw new NullPointerException("please add a Toolbar in your layout.");
        }
        setSupportActionBar(getToolbar());
    }

    public void setTitle(String strTitle,boolean showHome){
        setTitle(strTitle);
        getSupportActionBar().setDisplayShowHomeEnabled(showHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
    }

}
