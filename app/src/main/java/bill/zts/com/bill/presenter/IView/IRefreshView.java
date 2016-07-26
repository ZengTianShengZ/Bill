package bill.zts.com.bill.presenter.IView;

import java.util.List;

import mvp.zts.com.mvp_base.Presenter.IView.ISwipeRefreshView;

/**
 * Created by Administrator on 2016/7/26.
 */
public interface IRefreshView<T> extends ISwipeRefreshView {

    void fillInitData(List<T> mData);

    void appendMoreDataToView(List<T> mData);

    void hasNoMoreData();

}
