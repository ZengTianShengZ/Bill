package bill.zts.com.bill.ui.activity;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import bill.zts.com.bill.R;
import bill.zts.com.bill.utils.SharedPreferenceUtil;

/**
 * Created by Administrator on 2016/8/1.
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);
        //ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Back");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.framelayout, new PrefsFragement()).commit();
    }

    /**
     *   这样返回按钮才有效
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public static class PrefsFragement extends PreferenceFragment implements Preference.OnPreferenceClickListener{

        private String[] bill_type_str = {"学生型","职工型","日常型","休闲型"};

        private SharedPreferenceUtil mSharedPreferenceUtil;
        private Preference bill_type;
        private Preference change_update_time;
        private SwitchPreference notification_model;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);

            mSharedPreferenceUtil = SharedPreferenceUtil.getInstance(getActivity().getApplicationContext());


            bill_type = findPreference(SharedPreferenceUtil.BILL_TYPE);
            change_update_time = findPreference(SharedPreferenceUtil.AUTO_UPDATE);
            notification_model = (SwitchPreference) findPreference(SharedPreferenceUtil.NOTIFICATION_MODEL);

            change_update_time.setSummary(
                    mSharedPreferenceUtil.getAutoUpdate() == 0 ? "不用提醒" : "在 " + mSharedPreferenceUtil.getAutoUpdate() + " 点  提醒我");
            bill_type.setSummary(bill_type_str[mSharedPreferenceUtil.getBillType()]);

            bill_type.setOnPreferenceClickListener( this);
            change_update_time.setOnPreferenceClickListener(this);
            notification_model.setOnPreferenceClickListener(this);
        }


        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (bill_type == preference) {
                dialogSelectBillType();

            }
            if (change_update_time == preference) {

                showUpdateDialog();
            }else if (notification_model == preference) {
                notification_model.setChecked(notification_model.isChecked());
                mSharedPreferenceUtil.setNotificationModel(
                        notification_model.isChecked() ? Notification.FLAG_AUTO_CANCEL : Notification.FLAG_ONGOING_EVENT);

            }
            return false;
        }

        private void dialogSelectBillType(){
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogLayout = inflater.inflate(R.layout.dialog_select_bill_type,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(dialogLayout);
            final AlertDialog alertDialog = builder.create();

            final RadioButton radio_type_one = (RadioButton) dialogLayout.findViewById(R.id.radio_type_one);
            final RadioButton radio_type_tow = (RadioButton) dialogLayout.findViewById(R.id.radio_type_tow);
            final RadioButton radio_type_three = (RadioButton) dialogLayout.findViewById(R.id.radio_type_three);
            final RadioButton radio_type_fore = (RadioButton) dialogLayout.findViewById(R.id.radio_type_fore);

            TextView select_type_ok = (TextView) dialogLayout.findViewById(R.id.select_type_ok);

            radio_type_one.setClickable(true);
            radio_type_tow.setClickable(true);
            radio_type_three.setClickable(true);
            radio_type_fore.setClickable(true);

            radio_type_one.setChecked(false);
            radio_type_tow.setChecked(false);
            radio_type_three.setChecked(false);
            radio_type_fore.setChecked(false);

            switch (mSharedPreferenceUtil.getBillType()) {
                case 0:
                    radio_type_one.setChecked(true);
                    break;
                case 1:
                    radio_type_tow.setChecked(true);
                    break;
                case 2:
                    radio_type_three.setChecked(true);
                    break;
                case 3:
                    radio_type_fore.setChecked(true);
                    break;

            }

            select_type_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int select_flag = 0;
                    if(radio_type_one.isChecked()){
                        select_flag = 0;
                    }
                    if(radio_type_tow.isChecked()){
                        select_flag = 1;
                    }
                    if(radio_type_three.isChecked()){
                        select_flag = 2;
                    }
                    if(radio_type_fore.isChecked()){
                        select_flag = 3;
                    }
                    mSharedPreferenceUtil.setBillType(select_flag);
                    bill_type.setSummary(bill_type_str[select_flag]);
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        }

        private void showUpdateDialog() {
            //将 SeekBar 放入 Dialog 的方案 http://stackoverflow.com/questions/7184104/how-do-i-put-a-seek-bar-in-an-alert-dialog
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogLayout = inflater.inflate(R.layout.dialog_updata_time, (ViewGroup) getActivity().findViewById(
                    R.id.dialog_root));
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setView(dialogLayout);
            final AlertDialog alertDialog = builder.create();

            final SeekBar mSeekBar = (SeekBar) dialogLayout.findViewById(R.id.time_seekbar);
            final TextView tvShowHour = (TextView) dialogLayout.findViewById(R.id.tv_showhour);
            TextView tvDone = (TextView) dialogLayout.findViewById(R.id.done);

            mSeekBar.setMax(24);
            mSeekBar.setProgress(mSharedPreferenceUtil.getAutoUpdate());
            tvShowHour.setText(String.format("每天%s点整",mSeekBar.getProgress()));
            alertDialog.show();


            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    tvShowHour.setText(String.format("每天%s点整",mSeekBar.getProgress()));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            tvDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSharedPreferenceUtil.setAutoUpdate(mSeekBar.getProgress());
                    change_update_time.setSummary(
                            mSharedPreferenceUtil.getAutoUpdate() == 0 ? "不用提醒" : "每天" + mSharedPreferenceUtil.getAutoUpdate() + "点整提醒");
                    //需要再调用一次才能生效设置 不会重复的执行onCreate()， 而是会调用onStart()和onStartCommand()。
                    //getActivity().startService(new Intent(getActivity(), AutoUpdateService.class));
                    alertDialog.dismiss();
                }
            });

        }

    }
}
