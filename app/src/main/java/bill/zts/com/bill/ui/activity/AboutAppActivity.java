package bill.zts.com.bill.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.IView.IWebView;
import bill.zts.com.bill.presenter.WebViewPresenter;
import butterknife.Bind;
import mvp.zts.com.mvp_base.ui.activity.BaseSwipeRefreshActivity;

/**
 * Created by Administrator on 2016/8/2.
 */
public class AboutAppActivity  extends BaseSwipeRefreshActivity<WebViewPresenter> implements IWebView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.about_swipe_refresh_layout)
    SwipeRefreshLayout refresh_layout;
    @Bind(R.id.about_webview)
    WebView about_webview;

    @Override
    protected int getLayout() {
        return R.layout.activity_about_app;
    }
    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return refresh_layout;
    }
    @Override
    protected void initPresenter() {

        mPresenter = new WebViewPresenter(this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("About App",true);
        int accentColor = getApplicationContext().getResources().getColor(R.color.colorAccent);
        mPresenter.setLocalHtmlWeb(about_webview,"about_me.html",accentColor);
    }

    /**
     *   这样返回按钮才有效
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 将  mPrepareRefresh = true  ,下拉刷新 onRefreshStarted 才会调用
     */
    @Override
    protected void onRefreshStarted() {

    }
    @Override
    public void showEmptyView() {

    }

    @Override
    public void showErrorView(Throwable throwable) {

    }

}
