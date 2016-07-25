package mvp.zts.com.mvp_base.Presenter.IView;

/**
 * Created by Administrator on 2016/7/14.
 */
public interface ISwipeRefreshView extends IBaseView {


    void getDataFinish();

    void showEmptyView();

    void showErrorView(Throwable throwable);

    void showRefresh();

    void hideRefresh();
}
