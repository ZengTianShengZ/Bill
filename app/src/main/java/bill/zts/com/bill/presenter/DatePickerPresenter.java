package bill.zts.com.bill.presenter;

import android.app.Activity;
import android.text.TextUtils;

import bill.zts.com.bill.db.SQUtil;
import bill.zts.com.bill.presenter.IView.IDatePickerView;
import bill.zts.com.bill.ui.domain.DataInfo;
import mvp.zts.com.mvp_base.Presenter.BasePresenter;

/**
 * Created by Administrator on 2016/7/31.
 */
public class DatePickerPresenter extends BasePresenter<IDatePickerView>{
    public DatePickerPresenter(Activity context, IDatePickerView view) {
        super(context, view);
    }

    public void findTheDayData(String str_data){

        DataInfo dataInfo = SQUtil.findThisDayData(str_data);
        if(TextUtils.isEmpty(dataInfo.getTotalMoney())){
            mView.getNullData();
        }else {
            mView.getDataInfo(dataInfo);
        }
    }
}
