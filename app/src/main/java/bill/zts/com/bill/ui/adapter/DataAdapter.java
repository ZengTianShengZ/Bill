package bill.zts.com.bill.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.IView.ILoadeMoreDateView;
import bill.zts.com.bill.ui.domain.DataInfo;


/**
 * Created by Administrator on 2016/5/20.
 */
public class DataAdapter extends BaseRecycleViewAdapter<DataInfo>  {

    private Context mContext;


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
    public void bindBody(RecycleViewHolder holder, DataInfo dataInfo, int holderPosition) {

        TextView item_week_tv = holder.getView( R.id.item_week_tv);
        TextView item_data_tv = holder.getView( R.id.item_data_tv);
        TextView item_bill_tv = holder.getView( R.id.item_bill_tv);

        /*if(mMonthMap.get(holderPosition+1)!=null){
            item_week_tv.setText("!!!!!!!!!!!!!!!!!"+holderPosition);
        }else {

            item_week_tv.setText(""+dataInfo.getWeekInfo());
            item_data_tv.setText(""+dataInfo.getDataInfo());
        }*/


        item_week_tv.setText(""+dataInfo.getWeekInfo());
        item_data_tv.setText(""+dataInfo.getDataInfo());
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
        if( mILoadeMoreDateView!= null){
            mILoadeMoreDateView.loadMoreDate();
        }
    }

    private ILoadeMoreDateView mILoadeMoreDateView;
    public void setILoadeMoreDateView(ILoadeMoreDateView iLoadeMoreDateView){
        this.mILoadeMoreDateView = iLoadeMoreDateView;
    }
}
