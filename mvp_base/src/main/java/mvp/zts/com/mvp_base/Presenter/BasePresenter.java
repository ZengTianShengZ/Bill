package mvp.zts.com.mvp_base.Presenter;

import android.app.Activity;

import mvp.zts.com.mvp_base.Presenter.IView.IBaseView;

/**
 * Created by Administrator on 2016/7/14.
 */
public class BasePresenter<IV extends IBaseView> {

    protected IV mView;

    protected Activity mContext;

    public BasePresenter(Activity context, IV view) {
        mContext = context;
        mView = view;
    }
}
