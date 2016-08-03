package bill.zts.com.bill.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

import bill.zts.com.bill.utils.ConstantUtils;

/**
 * Created by Administrator on 2016/8/2.
 */
public class LauncherActivity extends Activity {
    private static final String TAG = LauncherActivity.class.getSimpleName();
    private SwitchHandler mHandler = new SwitchHandler(Looper.getMainLooper(), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//.............{"device_id":"863151020661978","mac":"38:bc:1a:9b:7a:56"}
        //MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setDebugMode(true);// 友盟调试模式开关[打印日志][上线时关闭]

        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    class SwitchHandler extends Handler {
        private WeakReference<LauncherActivity> mWeakReference;

        public SwitchHandler(Looper mLooper, LauncherActivity activity) {
            super(mLooper);
            mWeakReference = new WeakReference<LauncherActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Intent i = new Intent(LauncherActivity.this, MainActivity.class);
            LauncherActivity.this.startActivity(i);
            //activity切换的淡入淡出效果
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            LauncherActivity.this.finish();
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}