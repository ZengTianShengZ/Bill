package bill.zts.com.bill.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.db.SQUtil;
import bill.zts.com.bill.presenter.IView.IAdapterView;
import bill.zts.com.bill.ui.activity.MainActivity2;
import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DataInfo;
import bill.zts.com.bill.ui.domain.DeleteBillBean;
import bill.zts.com.bill.ui.domain.DeleteBillTagBean;
import bill.zts.com.bill.utils.NumAnim;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;


/**
 * Created by Administrator on 2016/5/20.
 */
public class DataAdapter extends BaseTypeRecycleViewAdapter<DataInfo> {

    private Context mContext;
    private MainActivity2 mActivityContext;
    private String[] str_bill = {"今","天","花","了","0元","！","！","！"};
    private String[] str_bill_menu = {"快点","来","记账","吧","！","！","！"};

    public DataAdapter(Context mContext, MainActivity2 activityContext, List<DataInfo> mListItems) {
        super(mContext, R.layout.list_item, mListItems);
        this.mContext  =mContext;
        this.mActivityContext = activityContext;
    }

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
        item_data_tv.setText(""+dataInfo.getDayInfo());

        // 要随机颜色 可以 到 ColorFactory 自己设置一组 颜色
        item_bill_tv.setTextColor(ColorFactory.onRandomBuild()[1]);
        item_bill_tv.setText(dataInfo.getTotalMoney()+"");

        tag_bill.setTags(str_bill);
        tag_bill_menu.setTags(str_bill_menu);

        setTagBill(tag_bill,tag_bill_menu,dataInfo.getBillList());




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

        setTagOnLongClick(tag_bill,tag_bill_menu,item_bill_tv,dataInfo.getBillList(),holderPosition);

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

    public void setTagOnLongClick(final TagContainerLayout tag_bill, final TagContainerLayout tag_bill_menu, final TextView item_bill_tv, final List<AddBillBean> billList, final int holderPosition){
        int click_Item = 0;

        if(null != tag_bill.getChildAt(0)){

            for(click_Item = 0; click_Item<tag_bill.getChildCount();click_Item++){

                DeleteBillBean deleteBillBean = new DeleteBillBean(click_Item,billList.get(click_Item));
                 // 给 监听的 view 设置 tag ，避免 点击view 出现标签混乱
                tag_bill.getChildAt(click_Item).setTag(deleteBillBean);

                tag_bill.getChildAt(click_Item).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(final View v) {
                        // v.getTag() 获得标签

                        final DeleteBillBean deleteBillBean = (DeleteBillBean) v.getTag();
                        final AddBillBean addBillBean = deleteBillBean.getAddBillBean();

                        StringBuilder tag_menuBuilder = new StringBuilder();
                        for (String tm:addBillBean.getTagList()){
                            tag_menuBuilder.append(tm+"  ");
                        }
                        new AlertDialog.Builder(mActivityContext,R.style.MyAlertDialogStyle).setTitle("确定删除 "+addBillBean.getStrMoney()+" ￥及其标签")
                                .setMessage(tag_menuBuilder)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        billList.remove(deleteBillBean.getDelete_position());

                                        SQUtil.deleteDataBean(mDatas.get(holderPosition).getIntData(),billList);

                                        setTagBill(tag_bill,tag_bill_menu,billList);
                                        // 重新设置了 setTagBill 也需要从新 setTagOnLongClick 点击事件才有效
                                        setTagOnLongClick(tag_bill,tag_bill_menu, item_bill_tv, billList, holderPosition);

                                        // 刷新  money 总数
                                        NumAnim.startAnim(item_bill_tv,computeTotleMoney(holderPosition));

                                        Toast.makeText(mContext,"确定确定确定",Toast.LENGTH_LONG).show();
                                    }
                                })
                                .show();


                        return false;
                    }
                });

            }

        }
    }

    private void setTagBill(final TagContainerLayout tag_bill,final TagContainerLayout tag_bill_menu,final List<AddBillBean> billList){
        if(null != billList){
            List<String> tagList = new ArrayList<String>();
            //String[] strTags = new String[dataInfo.getBillList().size()] ;
            List<String> strTags = new ArrayList<String>();
            int i = 0;
            for(AddBillBean billBean:billList) {

                strTags.add(billBean.getStrMoney()+" ￥");
                tagList.addAll(billBean.getTagList());

            }
            tag_bill.setTags(strTags);
            tag_bill_menu.setTags(tagList);
            // 我也不知道为什么这么做可以屏蔽 tag_bill_menu 点击事件，避免事件被 tag_bill_menu 的 tag 拿了。照成列表滑动卡顿。
            if(null != tag_bill_menu.getChildAt(0)){
                tag_bill_menu.getChildAt(0).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return false;
                    }
                });
            }
            strTags.clear();
            tagList.clear();

        }
    }

    public float computeTotleMoney(int position){
        float float_moneys = 0;
        for(AddBillBean billBean:mDatas.get(position).getBillList()){

            Float float_money = new Float(billBean.getStrMoney());
            float_moneys += float_money;
        }
        mDatas.get(position).setTotalMoney(float_moneys+"");
        return float_moneys;

    }

    private IAdapterView mIAdapterView;
    public void setILoadeMoreDateView(IAdapterView iLoadeMoreDateView){
        this.mIAdapterView = iLoadeMoreDateView;
    }
}
