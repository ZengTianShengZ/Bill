package bill.zts.com.bill.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Calendar;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.DatePickerPresenter;
import bill.zts.com.bill.presenter.IView.IDatePickerView;
import bill.zts.com.bill.ui.domain.DataInfo;
import bill.zts.com.bill.ui.fragment.DatePickerDialogFragment;
import butterknife.Bind;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import mvp.zts.com.mvp_base.ui.activity.BaseActivity;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;

public class DatePickerActivity extends BaseActivity<DatePickerPresenter> implements IDatePickerView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.av_datePicker)
    DatePicker picker;

    private int t_year;
    private int t_month;
    private int t_day;

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_date_picker;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new DatePickerPresenter(this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar(DatePickerActivity.this);
        initView();
        setTitle("Back",true);
    }

    public   void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
// 使用颜色资源
        tintManager.setStatusBarTintResource(R.color.pick_activity_darkColor);
    }

    @TargetApi(19)
    private   void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    private void initView() {

        Calendar c= Calendar.getInstance();//获取当前日期

        t_year = c.get(Calendar.YEAR);
        t_month = c.get(Calendar.MONTH)+1;
        t_day = c.get(Calendar.DAY_OF_MONTH);

        picker.setDate(t_year, t_month);
        // 设置节日 显示
        picker.setFestivalDisplay(true);
        picker.setTodayDisplay(true);
        // 设置休息日
        picker.setHolidayDisplay(true);
        picker.setDeferredDisplay(true);
        picker.setMode(DPMode.SINGLE);

        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {

                if(theDayIsNoComeIn(date)){
                    SnackbarUtil.PrimarySnackbar(mContext,picker,"   这一天还没到来 !!!");
                }else{
                    mPresenter.findTheDayData(date);
                }

                //Toast.makeText(DatePickerActivity.this, int_day+"", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean theDayIsNoComeIn(String date){
        String a[] = date.split("-");
        String p_year = a[0];
        String p_month = a[1];
        String p_day = a[2];
        Log.i("..","............"+p_year+p_month+p_day);
        if(t_month>Integer.parseInt(p_year)){
            return false;
        }else if(t_month >Integer.parseInt(p_month) ){
            return  false;
        }else if(t_day >=Integer.parseInt(p_day) ) {
            return  false;
        }
        return  true;
    }

    @Override
    public void getDataInfo(DataInfo dataInfo) {

        DatePickerDialogFragment.newInstance(dataInfo).show(getSupportFragmentManager(), "loginDialog");

    }

    @Override
    public void getNullData() {
        SnackbarUtil.PrimarySnackbar(mContext,picker,"   数据为空!!!");
    }
}