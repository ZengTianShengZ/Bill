package bill.zts.com.bill;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MyApplication  extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
    }
}
