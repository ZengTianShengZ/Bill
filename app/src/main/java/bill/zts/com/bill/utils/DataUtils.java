package bill.zts.com.bill.utils;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DataInfo;
import bill.zts.com.bill.ui.domain.SqBill;
import bill.zts.com.bill.ui.domain.SqBillItem;

/**
 * Created by Administrator on 2016/7/25.
 */
public class DataUtils {

    public static int Year_Count;
    public static int Month_Count;
    public static int Day_Count;
    private static Calendar mCalendar;
    private static  String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    static{
        mCalendar= Calendar.getInstance();//获取当前日期
        Year_Count = mCalendar.get(Calendar.YEAR);
        Month_Count = mCalendar.get(Calendar.MONTH);
        Day_Count = mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public static void getCurrentData(){

    }

    public static  List<DataInfo> getCurrentMonthDatas(){

        List<DataInfo> dataInfoList = new ArrayList<DataInfo>();
        for(int d = Day_Count; d>0;d--){
            int int_month = Month_Count+1;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Year_Count);
            stringBuilder.append(int_month);
            stringBuilder.append(d);

            int intData = Integer.parseInt(stringBuilder.toString());

            DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+int_month,""+getWeek(d),""+d,"0",intData);

            //  sq 数据库查询  ////////////////////////////////////////////////////////////////
            List<SqBill> sqBills =   DataSupport.where("intDay = ?",intData+"").find(SqBill.class);

            //SqBill sqBill = (SqBill) DataSupport.where("intDay = ?",intData+"").find(SqBill.class);
            //SqBill sqBill = DataSupport.find(SqBill.class, intData);
            Log.i("sqBill++++",".......sqBill fffffffffffffffff................."+sqBills);
            if(0!= sqBills.size()){
                Log.i("sqBill++++",".......sqBill fffffffffffffffff................."+sqBills.get(0));
                SqBill sqBill = sqBills.get(0);
                dataInfo.setTotalMoney(sqBill.getTotalMoney());
                dataInfo.setTestStr(sqBill.getTestStr()+"");

                List<AddBillBean> billBeen = new ArrayList<AddBillBean>();
                for(SqBillItem sqBillItem:sqBill.getBillList()){
                    AddBillBean addBillBean = new AddBillBean();
                    addBillBean.setStrMoney(sqBillItem.getStrMoney());
                    addBillBean.setTagList(sqBillItem.getTagList());

                    billBeen.add(addBillBean);
                }
                dataInfo.setBillList(billBeen);
            }

            ////////////////////////////////////////////////////////////////////////////////

            dataInfoList.add(dataInfo);
        }

        DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+Month_Count,"bottom","bottom","0",0);
        dataInfoList.add(dataInfo);
        return dataInfoList;
    }

    public static List<DataInfo> getNextMonthDatas(){
        Month_Count--;
        int int_month_flag = Month_Count;
        //Calendar 获取 月份 是 从 0 到 11 的，所以 这么要做一些判断
        if(Month_Count==0){
            int_month_flag = 12;
        }
        if(Month_Count == -1){
            Year_Count--;
            Month_Count = 11;
        }

        mCalendar.set(Year_Count,Month_Count,1);
        int lastday=mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<DataInfo> dataInfoList = new ArrayList<DataInfo>();
        for(int d = lastday; d>0;d--){
            int int_month = Month_Count+1;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Year_Count);
            stringBuilder.append(int_month);
            stringBuilder.append(d);

            int intData = Integer.parseInt(stringBuilder.toString());

            DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+int_month,""+getWeek(d),""+d,"0",intData);
            //System.out.println("....."+dataInfo.getMonthInfo()+"....."+dataInfo.getWeekInfo()+"..."+dataInfo.getDataInfo());
            dataInfoList.add(dataInfo);
        }
        DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+int_month_flag,"bottom","bottom","0",0);
        dataInfoList.add(dataInfo);
        return dataInfoList;
    }

    public static String getWeek(int int_data) {

        mCalendar.set(Year_Count,Month_Count,int_data);
        //System.out.println("..........int_week.........."+Year_Count+"-"+Month_Count+"-"+int_data);
        int int_week =  mCalendar.get(Calendar.DAY_OF_WEEK);
        //System.out.println("..........int_week.........."+int_week);
        return weekDays[int_week-1];
    }

}
