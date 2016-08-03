package bill.zts.com.bill.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

import bill.zts.com.bill.R;

/**
 * Created by Administrator on 2016/8/1.
 */
public class ConstantUtils {

    public static String[] StudentTag = {"早餐","午餐","晚餐","饮料","零食","日常用品","电影票","学习用品","车费","杂七杂八"};
    public static String[] OfficeWorkTag = {"早餐","车费","午餐","下午茶","买菜","晚餐","日常用品","应酬","水电费","杂七杂八"};
    public static String[] DailyTag = {"柴","米","油","盐","酱","醋","茶","厨房用品","浴室用品","水电费","车费","买菜","杂七杂八"};
    public static String[] LeisureTag = {"电影票","车票","酒店住宿","机票","景点门票","小吃","纪念品","服务费","杂七杂八"};

    public static String Token = "fb9af6b34774f96ac9ac30d840c00209";

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */

    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return context.getString(R.string.can_not_find_version_name);
        }
    }
}
