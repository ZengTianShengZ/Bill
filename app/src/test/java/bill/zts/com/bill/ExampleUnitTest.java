package bill.zts.com.bill;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(".........Calendar......"+year+".."+month+"..."+day);

        SimpleDateFormat    formatter    =   new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);
        System.out.println(".........str......"+str);




    }

    public static void getCurrentData(){
        Calendar cal_1= Calendar.getInstance();//获取当前日期
    }
}