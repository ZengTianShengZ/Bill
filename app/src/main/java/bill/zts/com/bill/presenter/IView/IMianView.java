package bill.zts.com.bill.presenter.IView;

import java.util.List;

import mvp.zts.com.mvp_base.Presenter.IView.IBaseView;
import mvp.zts.com.mvp_base.Presenter.IView.ISwipeRefreshView;

/**
 * Created by Administrator on 2016/7/25.
 */
public interface IMianView<T> extends IBaseView {

    void fillInitData(List<T> mData);

    void appendMoreDataToView(List<T> mData);

    void hasNoMoreData();


}
