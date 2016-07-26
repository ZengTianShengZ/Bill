package bill.zts.com.bill.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.ui.adapter.DataAdapter;
import bill.zts.com.bill.utils.DataUtils;

/**
 * Created by Administrator on 2016/7/26.
 */
public class activityTest extends AppCompatActivity {

    private RecyclerView mrecyclerview;
    private DataAdapter mDataAdapter;
    private  List<Integer> lis_int = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartest);
        mrecyclerview = (RecyclerView) findViewById(R.id.recyclerViewtest);
        setSupportActionBar(toolbar);

        int datas =  DataUtils.getCurrentMonthDatas();

        for(int i = datas;i>0;i--){
            lis_int.add(i);
        }


        initRecycleView();
    }

    private void initRecycleView() {
        mrecyclerview.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerview.setLayoutManager(llm);
        mDataAdapter = new DataAdapter(getApplication(),lis_int);
        mrecyclerview.setAdapter(mDataAdapter);
    }

}