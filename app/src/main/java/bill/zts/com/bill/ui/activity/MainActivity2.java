package bill.zts.com.bill.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.IView.IAdapterView;
import bill.zts.com.bill.presenter.IView.IBillDialogFragmentView;
import bill.zts.com.bill.presenter.IView.IMianView;
import bill.zts.com.bill.presenter.MainPresenter;
import bill.zts.com.bill.ui.adapter.DataAdapter;
import bill.zts.com.bill.ui.adapter.RecycleViewHolder;
import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DataInfo;
import bill.zts.com.bill.ui.fragment.EditBillDialogFragment;
import bill.zts.com.bill.utils.NumAnim;
import butterknife.Bind;
import co.lujun.androidtagview.TagContainerLayout;
import mvp.zts.com.mvp_base.ui.activity.BaseActivity;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MainActivity2 extends BaseActivity<MainPresenter>
        implements IMianView,IAdapterView,NavigationView.OnNavigationItemSelectedListener,
        IBillDialogFragmentView {


    @Bind(R.id.app_bar_SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private EditBillDialogFragment mEditBillDialog;

    private DataAdapter mDataAdapter;
    private  List<DataInfo> lis_int = new ArrayList<DataInfo>();
    private int editAdapterItemPosition;


    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }


    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this,this);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
       // mSwipeRefreshLayout.setRefreshing(true);
    }


    @Override
    public void onBackPressed() {
         if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
             mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initView() {

        setTitle("!");

        mSwipeRefreshLayout.setEnabled(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, DatePickerActivity.class);
                startActivity(intent);

            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initRecycleView();
    }

    private void initRecycleView() {
        mRecyclerview.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(llm);
        mDataAdapter = new DataAdapter(mContext,MainActivity2.this,lis_int);
        mRecyclerview.setAdapter(mDataAdapter);

    }

    private void initData() {
        mDataAdapter.setILoadeMoreDateView(this);
        mPresenter.getCurrentMonthDatas();
    }


    @Override
    public void fillInitData(List mData) {
        mDataAdapter.insertedAllItem(mData);
        mSwipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void adapterLoadMoreDate() {
        mPresenter.getNextMonthDatas();
    }

    @Override
    public void adapterEditBill(int holderPosition) {
        this.editAdapterItemPosition = holderPosition;
        mEditBillDialog = new EditBillDialogFragment();
        mEditBillDialog.setIBillDialogFragmentView(this);
        mEditBillDialog.show(getFragmentManager(), "loginDialog");

    }

    @Override
    public void appendMoreDataToView(List mData) {
        mDataAdapter.appendMoreItem(mData);

    }

    @Override
    public void hasNoMoreData() {

    }


    @Override
    public void getDialogBillList(List<AddBillBean> billList) {
        mEditBillDialog.dismiss();
        if(null != billList){

            DataInfo mDataInfo =  lis_int.get(editAdapterItemPosition) ;
            mDataInfo.getBillList().addAll(billList);

            RecycleViewHolder viewHolder  = (RecycleViewHolder) mRecyclerview.findViewHolderForAdapterPosition(editAdapterItemPosition);
            TagContainerLayout bill_menu = viewHolder.getView(R.id.list_item_tag_bill_menu);
            TagContainerLayout tag_bill =  viewHolder.getView( R.id.list_item_tag_bill);
            TextView item_bill_tv = viewHolder.getView( R.id.item_bill_tv);

            //float float_moneys = 0;
            for(AddBillBean addBillBean:billList){
                tag_bill.addTag(addBillBean.getStrMoney()+"");
                //Float float_money = new Float(addBillBean.getStrMoney());
                //float_moneys += float_money;
                for(String tag:addBillBean.getTagList()){
                    bill_menu.addTag(tag);
                }
            }
            /*if(!TextUtils.isEmpty(item_bill_tv.getText())){
                Float float_money = new Float(item_bill_tv.getText()+"");
                float_moneys += float_money;
            }*/

            //mDataInfo.setTotalMoney(float_moneys+"");
            /*float float_moneys = mDataAdapter.computeTotleMoney(editAdapterItemPosition);
            mDataInfo.setTotalMoney(float_moneys+"");
            NumAnim.startAnim(item_bill_tv,float_moneys);*/

            // 刷新  money 总数
            NumAnim.startAnim(item_bill_tv,mDataAdapter.computeTotleMoney(editAdapterItemPosition));

            mDataAdapter.setTagOnLongClick(tag_bill,bill_menu, item_bill_tv, mDataInfo.getBillList(), editAdapterItemPosition);
        }
    }
}
