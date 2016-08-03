package bill.zts.com.bill.presenter;

import android.app.Activity;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;

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
import mvp.zts.com.mvp_base.utils.SnackbarUtil;
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

        ApiInterface apiInterface =   RetrofitApi.retrofit.create(ApiInterface.class);

        apiInterface.mVersionAPI(ConstantUtils.Token).compose(RxUtils.<VersionAPI>rxSchedulerHelper())
                .subscribe(new Action1<VersionAPI>() {
                    @Override
                    public void call(VersionAPI versionAPI) {

                        String firVersionName = versionAPI.getVersionShort();
                        String currentVersionName = ConstantUtils.getVersion(mContext);
                        if (currentVersionName.compareTo(firVersionName) < 0) {
                            mView.upApkVersion(versionAPI);
                        }
                    }
                });

    }
}
