package bill.zts.com.bill.ui.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bill.zts.com.bill.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * Created by Administrator on 2016/7/27.
 */
public class EditBillDialogFragment extends DialogFragment {

    @Bind(R.id.edit_bill_noTv)
    TextView noTv;
    @Bind(R.id.edit_bill_yesTv)
    TextView yesTv;
    @Bind(R.id.edit_bill_addTag)
    TagContainerLayout addTag;
    @Bind(R.id.edit_bill_defaultTag)
    TagContainerLayout defaultTag;

    private String[] str1 = {"jiu","米油","盐","醋茶","柴酱醋茶"};
    private String[] str2 = {"柴酱醋茶","米油","盐","醋茶","柴酱醋茶","柴酱醋茶","米油","盐","醋茶","柴酱醋茶"};

    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_edit_bill_dialog, null);
        ButterKnife.bind(this,customView);


        initView();


        return new AlertDialog.Builder(getActivity()).setView(customView)
                .create();
    }

    private void initView() {

        addTag.setTags(str1);
        defaultTag.setTags(str2);

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
        noTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DialogFragmentDataImp imp = (DialogFragmentDataImp) getActivity();
                //imp.showMessage(getArguments().getString("message"));//对话框与Activity间通信，传递数据给实现了DialogFragmentDataImp接口的Activity
                dismiss();
            }
        });

    }

    @OnClick(R.id.edit_bill_yesTv) void submit() {
        dismiss();
    }

}

