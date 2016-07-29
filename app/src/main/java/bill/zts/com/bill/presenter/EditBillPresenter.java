package bill.zts.com.bill.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.presenter.IView.IEditBillView;
import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.fragment.EditBillDialogFragment;

/**
 * Created by Administrator on 2016/7/28.
 */
public class EditBillPresenter {

    private Context mContext;
    private IEditBillView mView;
    public EditBillPresenter(Activity context,IEditBillView mView){
        this.mContext = context;
        this.mView = mView;
    }


    public void setAddBillList(List<AddBillBean> adapterDatas, String text, List<String> tags){

        List<AddBillBean> billBeanList = new ArrayList<AddBillBean>();
        if(0 != adapterDatas.size()){
            billBeanList.addAll(adapterDatas);
        }
        if(!TextUtils.isEmpty(text)) {
            AddBillBean addBillBean = new AddBillBean();
            addBillBean.setStrMoney(text);
            addBillBean.setTagList(tags);
            billBeanList.add(addBillBean);
        }

        if(billBeanList.size() != 0){
            mView.getAddBillList(billBeanList);
        }

    }

}
