package bill.zts.com.bill.presenter;

import android.app.Activity;

import bill.zts.com.bill.presenter.IView.IMianView;
import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_base.Presenter.IView.IBaseView;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MainPresenter extends BasePresenter<IMianView> {

    public MainPresenter(Activity context, IMianView view) {
        super(context, view);
    }
}
