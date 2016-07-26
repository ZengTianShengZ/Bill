package bill.zts.com.bill.presenter;

import android.app.Activity;
import android.support.v4.app.INotificationSideChannel;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.presenter.IView.IMianView;
import bill.zts.com.bill.presenter.IView.IRefreshView;
import bill.zts.com.bill.utils.DataUtils;
import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_base.Presenter.IView.IBaseView;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MainPresenter extends BasePresenter<IRefreshView> {

    public MainPresenter(Activity context, IRefreshView view) {
        super(context, view);
    }

    public void getCurrentMonthDatas(){


        int datas =  DataUtils.getCurrentMonthDatas();
        List<Integer> lis_int = new ArrayList<Integer>();
        for(int i = datas;i>1;i--){
            lis_int.add(i);
        }
        mView.fillInitData(lis_int);
       // mView.getDataFinish();
    }
}
