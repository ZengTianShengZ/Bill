package bill.zts.com.bill.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.IView.IAdapterView;
import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DataInfo;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;


/**
 * Created by Administrator on 2016/5/20.
 */
public class DataAdapter extends BaseTypeRecycleViewAdapter<DataInfo> {

    private Context mContext;
    private String[] str = {"柴酱醋茶"};
    private String[] strnum = {"11111"};

    public DataAdapter(Context context, List<DataInfo> mListItems) {
        super(context, R.layout.list_item, mListItems);
        this.mContext  =context;
    }

/*

    @Override
    public void bindBody(RecycleViewHolder holder, Object obj, int holderPosition) {
        final DataInfo item = (DataInfo)obj;
        TextView item_week_tv = holder.getView( R.id.item_week_tv);
        TextView item_data_tv = holder.getView( R.id.item_data_tv);
        TextView item_bill_tv = holder.getView( R.id.item_bill_tv);

        item_week_tv.setText(""+item.getWeekInfo());
        item_data_tv.setText(""+item.getDataInfo());
    }

    @Override
    public void bindHead(RecycleViewHolder holder, Object obj, int holderPosition) {

        TextView context_Tv = holder.getView( R.id.list_item_head_tv);
        context_Tv.setText("bindHead");
    }

    @Override
    public void bindBottom(RecycleViewHolder holder, Object obj, int holderPosition) {

    }
*/

    @Override
    public void bindBody(RecycleViewHolder holder, DataInfo dataInfo, final int holderPosition) {

        // 这边有 holder.getView 重内存获取view 所以 一直 getView 是不会造成 屏幕闪动或性能影响的
        TextView item_week_tv = holder.getView( R.id.item_week_tv);
        TextView item_data_tv = holder.getView( R.id.item_data_tv);
        TextView item_bill_tv = holder.getView( R.id.item_bill_tv);
        ImageView item_edit_bill = holder.getView( R.id.list_item_edit_bill);

        TagContainerLayout tag_bill =  holder.getView( R.id.list_item_tag_bill);
        TagContainerLayout tag_bill_menu =  holder.getView( R.id.list_item_tag_bill_menu);

        item_week_tv.setText(""+dataInfo.getWeekInfo());
        item_data_tv.setText(""+dataInfo.getDataInfo());

        if(null != dataInfo.getBillList()){
            List<String> billList = new ArrayList<String>();
            //String[] strTags = new String[dataInfo.getBillList().size()] ;
            List<String> strTags = new ArrayList<String>();
            int i = 0;
            for(AddBillBean billBean:dataInfo.getBillList()) {

                strTags.add(billBean.getStrMoney()+" ￥");
                billList.addAll(billBean.getTagList());

            }
            tag_bill.setTags(strTags);
            tag_bill_menu.setTags(billList);
            strTags.clear();
            billList.clear();

        }


        item_bill_tv.setTextColor(ColorFactory.onRandomBuild()[0]);
        // Set customize theme
        tag_bill_menu.setTheme(ColorFactory.NONE);
        tag_bill_menu.setTagBackgroundColor(ColorFactory.onRandomBuild()[0]);

        item_edit_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mIAdapterView!= null){
                    mIAdapterView.adapterEditBill(holderPosition);
                }
            }
        });

        tag_bill_menu.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                Log.i(".tag_bill_menu..",".............tag_bill_menu...............");
                Toast.makeText(mContext,""+text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                Log.i(".tag_bill_menu..",".............tag_bill_menu...........ll....");
                Toast.makeText(mContext,""+text,Toast.LENGTH_LONG).show();
            }
        });
        tag_bill.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                Log.i(".tag_bill..",".............tag_bill...............");
                Toast.makeText(mContext,""+text,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                Log.i(".tag_bill..",".............tag_bill........lll.......");
                Toast.makeText(mContext,""+text,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void bindHead(RecycleViewHolder holder, DataInfo dataInfo, int holderPosition) {
        TextView item_month_tv = holder.getView( R.id.list_item_month_head_tv);
        item_month_tv.setText("-------"+dataInfo.getMonthInfo()+"-------");
    }

    @Override
    public void bindBottom(RecycleViewHolder holder, DataInfo dataInfo, int holderPosition) {


        Log.i("bindBottom","............bindBottom............");
        Toast.makeText(mContext,".....bindBottom......",Toast.LENGTH_LONG).show();
        if( mIAdapterView!= null){
            mIAdapterView.adapterLoadMoreDate();
        }
    }

    private IAdapterView mIAdapterView;
    public void setILoadeMoreDateView(IAdapterView iLoadeMoreDateView){
        this.mIAdapterView = iLoadeMoreDateView;
    }
}
