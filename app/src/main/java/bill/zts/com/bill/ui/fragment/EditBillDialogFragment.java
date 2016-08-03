package bill.zts.com.bill.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.EditBillPresenter;
import bill.zts.com.bill.presenter.IView.IBillDialogFragmentView;
import bill.zts.com.bill.presenter.IView.IEditBillView;
import bill.zts.com.bill.ui.adapter.DataAdapter;
import bill.zts.com.bill.ui.adapter.EditBillAdapter;
import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.utils.ConstantUtils;
import bill.zts.com.bill.utils.SharedPreferenceUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;

/**
 * Created by Administrator on 2016/7/27.
 */
public class EditBillDialogFragment extends DialogFragment implements IEditBillView {

    @Bind(R.id.edit_bill_noTv)
    TextView noTv;
    @Bind(R.id.edit_bill_yesTv)
    TextView yesTv;
    @Bind(R.id.edit_bill_addB_img)
    ImageView addB_img;
    @Bind(R.id.edit_bill_addTag_img)
    ImageView addTag_img;
    @Bind(R.id.edit_bill_edit)
    EditText editMoney;
    @Bind(R.id.edit_bill_addTag)
    TagContainerLayout addTag;
    @Bind(R.id.edit_bill_defaultTag)
    TagContainerLayout defaultTag;
    @Bind(R.id.edit_bill_RecyclerView)
    RecyclerView mRecyclerView;

    private View customView;

    private Context mContext;
    private EditBillAdapter mEditBillAdapter;
    private List<AddBillBean> billList = new ArrayList<AddBillBean>();
    private EditBillPresenter mEditBillPresenter;

    private String[] str1 = {"jiu","米油"};
    private String[] str2 = {"柴酱醋茶","柴酱醋茶","米油","盐","醋茶","柴酱醋茶","柴酱醋茶","米油","盐","醋茶","柴酱醋茶"};

    private IBillDialogFragmentView mIBillDialogFragmentView;
    public void setIBillDialogFragmentView(IBillDialogFragmentView mIBillDialogFragmentView){
        this.mIBillDialogFragmentView = mIBillDialogFragmentView;
    }


    public static EditBillDialogFragment newInstance(String message){
        //创建一个带有参数的Fragment实例
        EditBillDialogFragment fragment = new EditBillDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        fragment.setArguments(bundle);//把参数传递给该DialogFragment
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_edit_bill_dialog, null);
        ButterKnife.bind(this,customView);
        mContext = getActivity();

        mEditBillPresenter = new EditBillPresenter((Activity) mContext,this);

        initView();

        return customView;
    }
 /*    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setView(customView)
                .create();
    }*/

    private void initView() {
        initRecycleView();

        SharedPreferenceUtil mSharedPreferenceUtil = SharedPreferenceUtil.getInstance(getActivity().getApplicationContext());

        switch (mSharedPreferenceUtil.getBillType()) {
            case 0:
                defaultTag.setTags(ConstantUtils.StudentTag);
                break;
            case 1:
                defaultTag.setTags(ConstantUtils.OfficeWorkTag);
                break;
            case 2:
                defaultTag.setTags(ConstantUtils.DailyTag);
                break;
            case 3:
                defaultTag.setTags(ConstantUtils.LeisureTag);
                break;

        }

        addTag.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                addTag.removeTag(position);
            }

            @Override
            public void onTagLongClick(final int position, String text) {

            }
        });
        defaultTag.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                addTag.addTag(text);
            }

            @Override
            public void onTagLongClick(final int position, String text) {

            }
        });

        //mTvMsg.setText(getArguments().getString("message"));//把传递过来的数据设置给TextView


    }
    private void initRecycleView() {
        mRecyclerView.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mEditBillAdapter = new EditBillAdapter(mContext,billList);
        mRecyclerView.setAdapter(mEditBillAdapter);
    }


    @OnClick(R.id.edit_bill_yesTv) void yesTv() {

        if(0 == mEditBillAdapter.getAdapterDatas().size()&&(0 == addTag.getTags().size()||TextUtils.isEmpty(editMoney.getText()))){
            SnackbarUtil.PrimarySnackbar(mContext,noTv,"   你的 money 或 标签 不能为空 ! ! !");
        }else{
            mEditBillPresenter.setAddBillList(mEditBillAdapter.getAdapterDatas(),editMoney.getText()+"",addTag.getTags());
        }
    }
    @OnClick(R.id.edit_bill_noTv) void noTv() {
         dismiss();
    }

    @OnClick(R.id.edit_bill_addB_img) void addB_img() {

        if(TextUtils.isEmpty(editMoney.getText())){
            SnackbarUtil.PrimarySnackbar(mContext,noTv,"   你的Money不能为空!!!");
        }else if(0 == addTag.getTags().size()){
            SnackbarUtil.PrimarySnackbar(mContext,noTv,"   你的标签不能为空!!!");
        }else {

            AddBillBean addBillBean = new AddBillBean();
            addBillBean.setStrMoney(editMoney.getText()+"");
            addBillBean.setTagList(addTag.getTags());
            mEditBillAdapter.insertedItem(addBillBean);

            // 清空 编辑器 的 数据
            addTag.removeAllTags();
            editMoney.setText("");

        }
    }
    @OnClick(R.id.edit_bill_addTag_img) void addTag_img() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.dialog_custom_tag,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(dialogLayout);
        final AlertDialog alertDialog = builder.create();

        final TextView yesTv = (TextView) dialogLayout.findViewById(R.id.custom_tag_yesTv);
        final EditText tag_edit = (EditText) dialogLayout.findViewById(R.id.custom_tag_edit);

        alertDialog.show();

        tag_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 设置 弹出软键盘
                    alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        yesTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(tag_edit.getText())){
                    addTag.addTag(tag_edit.getText()+"");
                }
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void getAddBillList(List<AddBillBean> billList) {
        //dismiss();
        if(mIBillDialogFragmentView!= null){
            mIBillDialogFragmentView.getDialogBillList(billList);
        }
        dismiss();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        billList.clear();
    }
}

