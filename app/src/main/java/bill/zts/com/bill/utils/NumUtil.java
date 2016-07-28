package bill.zts.com.bill.utils;

/**
 * Created by Administrator on 2016/7/28.
 */
public class NumUtil {
    public static String NumberFormat(float f,int m){
        return String.format("%."+m+"f",f);
    }

    public static float NumberFormatFloat(float f,int m){
        String strfloat = NumberFormat(f,m);
        return Float.parseFloat(strfloat);
    }
}
