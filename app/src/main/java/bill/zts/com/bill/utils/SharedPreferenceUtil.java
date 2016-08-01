package bill.zts.com.bill.utils;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by hugo on 2016/2/19 0019.
 *
 * 设置相关 包括 sp 的写入
 */
public class SharedPreferenceUtil {

    public static final String AUTO_UPDATE = "change_update_time"; //自动更新时长
    public static final String BILL_TYPE = "bill_type"; //自动更新时长

    public static final String NOTIFICATION_MODEL = "notification_model";

    public static int ONE_HOUR = 1000 * 60 * 60;

    private static SharedPreferenceUtil sInstance;

    private SharedPreferences mPrefs;

    public static SharedPreferenceUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferenceUtil(context);
        }
        return sInstance;
    }

    private SharedPreferenceUtil(Context context) {
        mPrefs = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        //mPrefs.edit().putInt(CHANGE_ICONS, 1).apply();
    }


    public SharedPreferenceUtil putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public SharedPreferenceUtil putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }


    // 设置账单类型
    public void setBillType(int t){
        mPrefs.edit().putInt(BILL_TYPE,t).apply();
    }
    public int getBillType(){
        return mPrefs.getInt(BILL_TYPE,0);
    }

    // 自动更新时间 hours
    public void setAutoUpdate(int t) {
        mPrefs.edit().putInt(AUTO_UPDATE, t).apply();
    }

    public int getAutoUpdate() {
        return mPrefs.getInt(AUTO_UPDATE, 3);
    }

    //  通知栏模式 默认为常驻
    public void setNotificationModel(int t) {
        mPrefs.edit().putInt(NOTIFICATION_MODEL, t).apply();
    }
    public int getNotificationModel() {
        return mPrefs.getInt(NOTIFICATION_MODEL, Notification.FLAG_AUTO_CANCEL);
    }


}
