package bill.zts.com.bill.presenter.IView;

import bill.zts.com.bill.ui.domain.DataInfo;
import mvp.zts.com.mvp_base.Presenter.IView.IBaseView;

/**
 * Created by Administrator on 2016/7/31.
 */
public interface IDatePickerView<T> extends IBaseView {

    void getDataInfo( DataInfo dataInfo);
    void getNullData();
}
