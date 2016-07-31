package bill.zts.com.bill.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import bill.zts.com.bill.R;
import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DataInfo;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;

/**
 * Created by Administrator on 2016/7/31.
 */
public class DatePickerDialogFragment extends DialogFragment {

    @Bind(R.id.dialog_picker_day_tv)
    TextView day_tv;
    @Bind(R.id.dialog_picker_total_money)
    TextView total_money;
    @Bind(R.id.dialog_picker_close_tv)
    TextView close_tv;

    @Bind(R.id.picker_tag_bill_money)
    TagContainerLayout tag_bill_money;
    @Bind(R.id.picker_tag_bill_menu)
    TagContainerLayout tag_bill_menu;


    private Context mContext;
    private static DataInfo dataInfo;

    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
    }

    public static DatePickerDialogFragment newInstance(DataInfo dataInfos){
         dataInfo = dataInfos;
        //创建一个带有参数的Fragment实例
        DatePickerDialogFragment fragment = new DatePickerDialogFragment ();
        /*Bundle bundle = new Bundle();
        bundle.putString("message", message);
        fragment.setArguments(bundle);//把参数传递给该DialogFragment*/
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_data_picker, null);
        ButterKnife.bind(this,customView);
        mContext = getActivity();

        initView();

        return new AlertDialog.Builder(getActivity()).setView(customView)
                .create();
    }



    private void initView() {

        day_tv.setText(dataInfo.getMonthInfo()+"    "+dataInfo.getWeekInfo());
        total_money.setText("总钱： "+dataInfo.getTotalMoney()+" ￥");

        for(AddBillBean addBillBean:dataInfo.getBillList()){
            //tag_bill_money.setTag(addBillBean.getStrMoney()+"");
            tag_bill_money.addTag(addBillBean.getStrMoney()+" ￥");
            for (String str :addBillBean.getTagList()){
                //tag_bill_menu.setTag(str+"");
                tag_bill_menu.addTag(str+"");
            }
        }
    }

    @OnClick(R.id.dialog_picker_close_tv) void close_tv() {
        dismiss();
    }



}
