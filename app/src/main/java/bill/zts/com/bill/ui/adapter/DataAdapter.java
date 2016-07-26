package bill.zts.com.bill.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.List;

import bill.zts.com.bill.R;


/**
 * Created by Administrator on 2016/5/20.
 */
public class DataAdapter extends BaseRecycleViewAdapter   {

    private Context mContext;


    public DataAdapter(Context context, List<Integer> mListItems) {
        super(context, R.layout.list_item, mListItems);
        this.mContext  =context;
    }


    @Override
    public void bindBody(RecycleViewHolder holder, Object obj, int holderPosition) {
        final Integer item = (Integer)obj;
        TextView context_Tv = holder.getView( R.id.list_item_tv);
        context_Tv.setText(""+item);
    }

    @Override
    public void bindHead(RecycleViewHolder holder, Object obj, int holderPosition) {

        TextView context_Tv = holder.getView( R.id.list_item_head_tv);
        context_Tv.setText("bindHead");
    }

    @Override
    public void bindBottom(RecycleViewHolder holder, Object obj, int holderPosition) {

    }

}
