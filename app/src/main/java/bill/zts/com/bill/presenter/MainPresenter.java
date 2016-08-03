package bill.zts.com.bill.presenter;

import android.app.Activity;
import android.support.v4.app.INotificationSideChannel;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.http.ApiInterface;
import bill.zts.com.bill.http.RetrofitApi;
import bill.zts.com.bill.presenter.IView.IMianView;
import bill.zts.com.bill.presenter.IView.IRefreshView;
import bill.zts.com.bill.ui.domain.VersionAPI;
import bill.zts.com.bill.utils.ConstantUtils;
import bill.zts.com.bill.utils.DataUtils;
import bill.zts.com.bill.utils.RxUtils;
import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_base.Presenter.IView.IBaseView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MainPresenter extends BasePresenter<IMianView> {

    public MainPresenter(Activity context, IMianView view) {
        super(context, view);
    }

    public void getCurrentMonthDatas(){

        mView.fillInitData(DataUtils.getCurrentMonthDatas());

    }

    public void getNextMonthDatas(){
        mView.appendMoreDataToView(DataUtils.getNextMonthDatas());
    }

    public void onActivityDestory(){
        DataUtils.resetCurrentDay();
    }

    public void getVersion(){
        ApiInterface apiInterface = (ApiInterface) RetrofitApi.getApiService(VersionAPI.class);

        apiInterface.mVersionAPI(ConstantUtils.Token).compose(RxUtils.<VersionAPI>rxSchedulerHelper())
                .subscribe(new Action1<VersionAPI>() {
                    @Override
                    public void call(VersionAPI versionAPI) {

                    }
                });


    }
}
