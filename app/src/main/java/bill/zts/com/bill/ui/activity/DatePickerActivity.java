package bill.zts.com.bill.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

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
        initView();
        setTitle("Back",true);
    }


    private void initView() {

        Calendar c= Calendar.getInstance();//获取当前日期

        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH)+1;
        final int day = c.get(Calendar.DAY_OF_MONTH);

        picker.setDate(year, month);
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
                String str_day = date.replaceAll("-","");
                int int_day = Integer.parseInt(str_day);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append( String.valueOf(year));
                stringBuilder.append( String.valueOf(month));
                stringBuilder.append( String.valueOf(day));
                stringBuilder.toString();

                Log.i("..","......stringBuilder......."+stringBuilder.toString());
                int parseInt = Integer.parseInt(stringBuilder.toString());
                Log.i("..","......stringBuilder....parseInt..."+ parseInt);

                int current_day = Integer.parseInt(year+""+month+""+day+"");

                Log.i("..",".....current_day........"+current_day);

                Log.i("..",".....int_day........"+int_day);

                if(parseInt<int_day){
                    SnackbarUtil.PrimarySnackbar(mContext,picker,"   这一天还没到来 !!!");
                }else{
                    mPresenter.findTheDayData(date);
                }

                //Toast.makeText(DatePickerActivity.this, int_day+"", Toast.LENGTH_LONG).show();
            }
        });

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