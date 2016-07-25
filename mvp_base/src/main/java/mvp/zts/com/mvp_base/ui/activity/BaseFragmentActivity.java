package mvp.zts.com.mvp_base.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;

import butterknife.Bind;
import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_base.R;
import mvp.zts.com.mvp_base.ui.fragment.BaseFragment;

/**
 * Created by Administrator on 2016/7/16.
 */
public abstract class BaseFragmentActivity<P extends BasePresenter> extends BaseActivity<P>  {

    protected LinearLayout base_fragment_layout;

   // protected Toolbar mToolbar;
    protected BaseFragment mHomeFragment;
    protected FragmentManager fm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mToolbar = (Toolbar) findViewById(R.id.toolbar);
        base_fragment_layout = (LinearLayout) findViewById(R.id.base_fragment_layout);
        checkLayoutIsNoInclude();
        initFragment();

    }

    private void  checkLayoutIsNoInclude(){
        if(base_fragment_layout == null){
            throw new NullPointerException("please add  <include layout=\"@layout/base_fragment_activity\"/> in your layout.");
        }
    }

    private void initFragment() {
        fm = getSupportFragmentManager() ;
        FragmentTransaction mTransaction = fm.beginTransaction();

        if(mHomeFragment==null){
            mHomeFragment = initHomeFragment();
            mTransaction.add(R.id.fragement_layout, mHomeFragment);
        }else{
            mTransaction.show(mHomeFragment);
        }
        mTransaction.commit();


    }

    protected abstract BaseFragment initHomeFragment();
}
