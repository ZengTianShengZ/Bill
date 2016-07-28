package bill.zts.com.bill.presenter.IView;

import java.util.List;

import bill.zts.com.bill.ui.domain.AddBillBean;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface IEditBillView {
    void getAddBillList(List<AddBillBean> billList);
}
