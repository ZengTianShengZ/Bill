package bill.zts.com.bill.utils;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class DataUtils {

    public static int Year_Count;
    public static int Month_Count;
    public static int Day_Count;

    static{
        Calendar c= Calendar.getInstance();//获取当前日期
        Year_Count = c.get(Calendar.YEAR);
        Month_Count = c.get(Calendar.MONTH)+1;
        Day_Count = c.get(Calendar.DAY_OF_MONTH);
    }

    public static void getCurrentData(){

    }

    public int getCurrentMonthDatas(){
        return Day_Count;
    }

}
