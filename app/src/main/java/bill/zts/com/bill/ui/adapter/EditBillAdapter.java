package bill.zts.com.bill.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.ui.domain.AddBillBean;
import co.lujun.androidtagview.TagContainerLayout;

/**
 * Created by Administrator on 2016/7/27.
 */
public class EditBillAdapter extends BaseRecycleViewAdapter<AddBillBean> {


    public EditBillAdapter(Context mContext, List<AddBillBean> billList) {
        super(mContext, R.layout.list_item_add_bill, billList);
    }


    @Override
    public void convert(RecycleViewHolder holder, AddBillBean addBillBean, final int holderPosition) {

        TextView add_bill_tv = holder.getView( R.id.add_bill_tv);
        ImageView deleteImg = holder.getView( R.id.add_bill_deleteImg);
        TagContainerLayout add_bill_tag =  holder.getView( R.id.add_bill_tag);

        add_bill_tv.setText(addBillBean.getStrMoney()+" $");
        add_bill_tag.setTags(addBillBean.getTagList());

        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePosition(holderPosition);
            }
        });
    }
}
