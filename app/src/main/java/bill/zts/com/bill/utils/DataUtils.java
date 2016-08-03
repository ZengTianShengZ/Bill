package bill.zts.com.bill.utils;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import bill.zts.com.bill.db.SQUtil;

import bill.zts.com.bill.ui.domain.DataInfo;


/**
 *   DataUtils
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
    public static void resetCurrentDay(){
        mCalendar= Calendar.getInstance();//获取当前日期
        Year_Count = mCalendar.get(Calendar.YEAR);
        Month_Count = mCalendar.get(Calendar.MONTH);
        Day_Count = mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到当月 的 数据
     * @return  List<DataInfo>
     */
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

            // 数据库 得到 数据
            SQUtil.findDataBean(intData, dataInfo);

            dataInfoList.add(dataInfo);
        }

        DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+Month_Count,"bottom","bottom","0",0);
        dataInfoList.add(dataInfo);
        return dataInfoList;
    }

    /**
     *  得到下一个月的数据
     * @return List<DataInfo>
     */
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

            // 数据库 得到 数据
            SQUtil.findDataBean(intData, dataInfo);

            dataInfoList.add(dataInfo);
        }
        DataInfo dataInfo = new DataInfo(""+Year_Count+"-"+int_month_flag,"bottom","bottom","0",0);
        dataInfoList.add(dataInfo);
        return dataInfoList;
    }

    /**
     *  计算 这一天 是星期几
     * @param int_data 12
     * @return String
     */
    public static String getWeek(int int_data) {
         mCalendar.set(Year_Count,Month_Count,int_data);
         int int_week =  mCalendar.get(Calendar.DAY_OF_WEEK);
         return weekDays[int_week-1];
    }

}
