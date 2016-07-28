package bill.zts.com.bill.presenter.IView;

import java.util.List;

import bill.zts.com.bill.ui.domain.AddBillBean;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface IBillDialogFragmentView {

    void getDialogBillList(List<AddBillBean> billList);

}
