package bill.zts.com.bill.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.umeng.analytics.MobclickAgent;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.IView.IWebView;
import bill.zts.com.bill.presenter.WebViewPresenter;
import bill.zts.com.bill.utils.view.SystemBarColor;
import butterknife.Bind;
import mvp.zts.com.mvp_base.ui.activity.BaseSwipeRefreshActivity;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;

/**
 * Created by Administrator on 2016/8/2.
 */
public class AboutAppActivity  extends BaseSwipeRefreshActivity<WebViewPresenter> implements IWebView , AppBarLayout.OnOffsetChangedListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.about_webview)
    WebView about_webview;
    @Bind(R.id.about_swipe_refresh_layout)
    SwipeRefreshLayout refresh_layout;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @Override
    protected int getLayout() {
        return R.layout.t;
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
        SystemBarColor.initSystemBar(AboutAppActivity.this,R.color.colorOverall);
        setTitle("About App",true);

        about_webview.addJavascriptInterface(new WebAppInterface(getApplicationContext()),"Android");


        int accentColor = getApplicationContext().getResources().getColor(R.color.colorAccent);
        mPresenter.setLocalHtmlWeb(about_webview,"about_app.html",accentColor);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            refresh_layout.setEnabled(true);
        } else {
            refresh_layout.setEnabled(false);
        }
    }
    /**
     * 这样返回按钮才有效
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
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        appBarLayout.addOnOffsetChangedListener(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        appBarLayout.removeOnOffsetChangedListener(this);
    }



    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void checkNewVersion(){
            SnackbarUtil.PrimarySnackbar(mContext,about_webview," 已经是  最新版本 !!!");

        }
        @JavascriptInterface
        public void shareApp(){

        }
    }
}
