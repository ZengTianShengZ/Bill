package bill.zts.com.bill.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
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
        getFragmentManager().beginTransaction().replace(R.id.framelayout, new PrefsFragement()).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Back");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    mSharedPreferenceUtil.getAutoUpdate() == 0 ? "不用提醒" : "在" + mSharedPreferenceUtil.getAutoUpdate() + "提醒我");

            bill_type.setOnPreferenceClickListener( this);
            change_update_time.setOnPreferenceClickListener(this);
            notification_model.setOnPreferenceClickListener(this);
        }


        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (bill_type == preference) {
                dialogSelectBillType();
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

            radio_type_one.setClickable(false);
            radio_type_tow.setClickable(false);
            radio_type_three.setClickable(false);
            radio_type_fore.setClickable(false);

            switch (mSharedPreferenceUtil.getBillType()) {
                case 0:
                    radio_type_one.setClickable(true);
                    break;
                case 1:
                    radio_type_tow.setClickable(true);
                    break;
                case 2:
                    radio_type_three.setClickable(true);
                    break;
                case 3:
                    radio_type_fore.setClickable(true);
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
                }
            });

            alertDialog.show();
        }



    }
}
