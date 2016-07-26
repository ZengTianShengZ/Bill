package bill.zts.com.bill.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bill.zts.com.bill.ui.domain.DataInfo;

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
            DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+int_month,""+getWeek(d),""+d);
            //System.out.println("....."+dataInfo.getMonthInfo()+"....."+dataInfo.getWeekInfo()+"..."+dataInfo.getDataInfo());
            dataInfoList.add(dataInfo);
        }
        DataInfo dataInfo = new DataInfo("bottom","bottom","bottom");
        dataInfoList.add(dataInfo);
        return dataInfoList;
    }

    public static List<DataInfo> getNextMonthDatas(){
        Month_Count--;
        if(Month_Count == -1){
            Year_Count--;
            Month_Count = 11;
        }
        mCalendar.set(Year_Count,Month_Count,1);
        int lastday=mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<DataInfo> dataInfoList = new ArrayList<DataInfo>();
        for(int d = lastday; d>0;d--){
            int int_month = Month_Count+1;
            DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+int_month,""+getWeek(d),""+d);
            //System.out.println("....."+dataInfo.getMonthInfo()+"....."+dataInfo.getWeekInfo()+"..."+dataInfo.getDataInfo());
            dataInfoList.add(dataInfo);
        }
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
