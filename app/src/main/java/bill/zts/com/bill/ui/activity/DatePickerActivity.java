package bill.zts.com.bill.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.R;
import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class DatePickerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_date_picker);



        DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
        picker.setDate(2016, 7);
        // 设置节日 显示
        picker.setFestivalDisplay(true);
        picker.setTodayDisplay(true);
        // 设置休息日
        picker.setHolidayDisplay(true);
        picker.setDeferredDisplay(true);
        picker.setMode(DPMode.SINGLE);

        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Toast.makeText(DatePickerActivity.this, date, Toast.LENGTH_LONG).show();
            }
        });

        // 对话框下的DatePicker示例 Example in dialog
        Button btnPick = (Button) findViewById(R.id.main_btn);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(DatePickerActivity.this).create();
                dialog.show();
                DatePicker picker = new DatePicker(DatePickerActivity.this);
                picker.setDate(2015, 10);
                picker.setMode(DPMode.SINGLE);
                picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
                    @Override
                    public void onDatePicked(String date) {
                        Toast.makeText(DatePickerActivity.this, date, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setContentView(picker, params);
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });

        String[] str_bill_menu = {"快点","来","记账","吧","！","！","！"};
        TagContainerLayout tag = (TagContainerLayout) findViewById(R.id.tag_bill_menueeee);
        tag.setTags(str_bill_menu);
        tag.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Toast.makeText(getApplicationContext(),"onTagClick",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTagLongClick(int position, String text) {
                Toast.makeText(getApplicationContext(),"onTagLongClick",Toast.LENGTH_LONG).show();
            }
        });
    }
}