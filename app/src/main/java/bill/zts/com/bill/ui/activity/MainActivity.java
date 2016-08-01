package bill.zts.com.bill.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.db.SQUtil;
import bill.zts.com.bill.presenter.IView.IAdapterView;
import bill.zts.com.bill.presenter.IView.IBillDialogFragmentView;
import bill.zts.com.bill.presenter.IView.IMianView;
import bill.zts.com.bill.presenter.MainPresenter;
import bill.zts.com.bill.ui.adapter.DataAdapter;
import bill.zts.com.bill.ui.adapter.RecycleViewHolder;
import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DataInfo;
import bill.zts.com.bill.ui.fragment.EditBillDialogFragment;
import bill.zts.com.bill.utils.DoubleClickExit;
import bill.zts.com.bill.utils.NumAnim;
import bill.zts.com.bill.utils.view.RecycleViewDivider;
import bill.zts.com.bill.utils.view.SystemBarColor;
import butterknife.Bind;
import co.lujun.androidtagview.TagContainerLayout;
import mvp.zts.com.mvp_base.ui.activity.BaseActivity;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MainActivity extends BaseActivity<MainPresenter>
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
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private EditBillDialogFragment mEditBillDialog;

    private DataAdapter mDataAdapter;
    private  List<DataInfo> lis_int = new ArrayList<DataInfo>();
    private int editAdapterItemPosition;
    private int darkVibrantColor;
    private int lightVibrantColor;


    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbarColor();
        initView();
        initData();
    }

    private void initToolbarColor() {
        SystemBarColor.initSystemBar(MainActivity.this,R.color.pick_activity_darkColor);

/*
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bill_mater);
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultColor = getResources().getColor(R.color.medium_blue);
                int defaultTitleColor = getResources().getColor(R.color.white);
                darkVibrantColor = palette.getDarkVibrantColor(defaultColor);
                lightVibrantColor = palette.getLightVibrantColor(defaultTitleColor);

                mCollapsingToolbarLayout.setContentScrimColor(lightVibrantColor);
                mCollapsingToolbarLayout.setCollapsedTitleTextColor(darkVibrantColor);
                mCollapsingToolbarLayout.setExpandedTitleColor(darkVibrantColor);


            }
        });*/

    }
    private void initView() {

        setTitle("Bill");

        mSwipeRefreshLayout.setEnabled(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DatePickerActivity.class);
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
        //mRecyclerview 分割线
        mRecyclerview.addItemDecoration(new RecycleViewDivider(getBaseContext(),
                LinearLayoutManager.HORIZONTAL,20,getResources().getColor(R.color.white)));
        mDataAdapter = new DataAdapter(mContext,MainActivity.this,lis_int);
        mRecyclerview.setAdapter(mDataAdapter);

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
            if (!DoubleClickExit.check()) {
                SnackbarUtil.PrimarySnackbar(mContext,mDrawerLayout,"   再按一次退出应用  !!!");
            } else {
                finish();
            }
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_set) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutAppActivity.class));
        } else if (id == R.id.nav_contact_me) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
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

            float total_money = mDataAdapter.computeTotleMoney(editAdapterItemPosition);
            // 将 数据 存入 数据库
            SQUtil.setDataBean(mDataInfo.getIntData(), billList);


            for(AddBillBean addBillBean:billList){
                tag_bill.addTag(addBillBean.getStrMoney()+" ￥");

                for(String tag:addBillBean.getTagList()){
                    bill_menu.addTag(tag);
                }
            }

            // 刷新  money 总数
            NumAnim.startAnim(item_bill_tv,total_money);

            mDataAdapter.setTagOnLongClick(tag_bill,bill_menu, item_bill_tv, mDataInfo.getBillList(), editAdapterItemPosition);
        }
    }

}
