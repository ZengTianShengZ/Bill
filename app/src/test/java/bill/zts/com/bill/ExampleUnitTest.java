package bill.zts.com.bill;

import android.provider.ContactsContract;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bill.zts.com.bill.utils.DataUtils;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        System.out.println(".........addition_isCorrect......");


        Calendar c= Calendar.getInstance();//获取当前日期

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int lastday=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int week = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(".........Calendar......"+year+".."+month +"..."+day+"..."+lastday+"..."+week);


       c.set(year,month,day-1);

        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH)+1;
        int day1 = c.get(Calendar.DAY_OF_MONTH);
        int lastday1=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int week1 = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(".........Calendar......"+year1+".."+month1+"..."+day1+"..."+lastday1+"..."+week1);

        c.set(year,month,day-2);

        int year11 = c.get(Calendar.YEAR);
        int month11 = c.get(Calendar.MONTH)+1;
        int day11 = c.get(Calendar.DAY_OF_MONTH);
        int lastday11=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int week11 = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(".........Calendar......"+year11+".."+month11+"..."+day11+"..."+lastday11+"..."+week11);

        c.set(year,month,day-3);

        int year111 = c.get(Calendar.YEAR);
        int month111 = c.get(Calendar.MONTH)+1;
        int day111 = c.get(Calendar.DAY_OF_MONTH);
        int lastday111=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int week111 = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(".........Calendar......"+year111+".."+month111+"..."+day111+"..."+lastday111+"..."+week111);

        c.set(year,month,day-4);

        int year121 = c.get(Calendar.YEAR);
        int month121 = c.get(Calendar.MONTH)+1;
        int day121 = c.get(Calendar.DAY_OF_MONTH);
        int lastday121=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int week121 = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(".........Calendar......"+year121+".."+month121+"..."+day121+"..."+lastday121+"..."+week121);

        c.set(year,month,day-5);

        int year1212 = c.get(Calendar.YEAR);
        int month1212 = c.get(Calendar.MONTH)+1;
        int day1212 = c.get(Calendar.DAY_OF_MONTH);
        int lastday1212=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int week1212 = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(".........Calendar......"+year1212+".."+month1212+"..."+day1212+"..."+lastday1212+"..."+week1212);

        c.set(year,month,day-6);

        int year1213 = c.get(Calendar.YEAR);
        int month1213 = c.get(Calendar.MONTH)+1;
        int day1213 = c.get(Calendar.DAY_OF_MONTH);
        int lastday1213=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int week1213 = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(".........Calendar......"+year1213+".."+month1213+"..."+day1213+"..."+lastday1213+"..."+week1213);


        DataUtils.getCurrentMonthDatas();


     /*   java.util.Date dddd = new java.util.Date();

        System.out.println(".........dddd......"+ dddd);
        System.out.println(".........getWeekOfDate......"+ getWeekOfDate(dddd));
        c.add(Calendar.MONTH, -1);    //得到前一个月


        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH)+1;
        int day1 = c.get(Calendar.DAY_OF_MONTH);
        int lastday1=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(".........Calendar......"+year1+".."+month1+"..."+day1+"..."+lastday1);
*/
    }
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    public static void getCurrentData(){
        Calendar cal_1= Calendar.getInstance();//获取当前日期
    }
}