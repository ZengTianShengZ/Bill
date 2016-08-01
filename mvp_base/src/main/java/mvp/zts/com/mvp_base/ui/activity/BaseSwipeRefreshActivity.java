package mvp.zts.com.mvp_base.ui.activity;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.v4.widget.SwipeRefreshLayout;

import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_base.Presenter.IView.ISwipeRefreshView;
import mvp.zts.com.mvp_base.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public abstract class BaseSwipeRefreshActivity<P extends BasePresenter> extends BaseActivity<P> implements ISwipeRefreshView {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();


    /**
     * check data status
     * 需要的话可以重写此方法，比如做一些网络判断，来是否开启 mSwipeRefreshLayout 的状态
     * @return return true indicate it should load data really else indicate don't refresh
     */
    protected boolean mPrepareRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSwipeLayout();
    }

    private void initSwipeLayout(){

        if(getSwipeRefreshLayout()==null){
            throw new NullPointerException("please add a SwipeRefreshLayout in your layout.");
        }
        mSwipeRefreshLayout = getSwipeRefreshLayout();

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mPrepareRefresh) {
                    onRefreshStarted();
                } else {
                    //产生一个加载数据的假象
                    hideRefresh();
                }
            }
        });
    }


    /**
     * the method of get data
     */
    protected abstract void onRefreshStarted();

    @Override
    public void hideRefresh() {
        // 防止刷新消失太快，让子弹飞一会儿. do not use lambda!!
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mSwipeRefreshLayout != null){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        },2000);
    }

    @Override
    public void showRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    /**
     * check refresh layout is refreshing
     * @return if the refresh layout is refreshing return true else return false
     */
    @CheckResult
    protected boolean isRefreshing(){
        return mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public void getDataFinish() {
        hideRefresh();
    }

}
