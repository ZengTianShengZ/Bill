package bill.zts.com.bill.utils;

import android.util.Log;

import java.util.List;

import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DeleteBillTagBean;

/**
 * 计算要删除的 tag 标签的 位置信息
 */
public class DeleteBillInfoUtil {

    public static DeleteBillTagBean deleteInfo(int click_Item, List<AddBillBean> billList){


        int tag_menu_start_position = 0;
        for(int i=0;i<click_Item;i++){
            tag_menu_start_position += billList.get(i).getTagList().size(); //4+3
        }
        int tag_menu_end_position = 0;
        tag_menu_end_position = tag_menu_start_position + billList.get(click_Item).getTagList().size()-1;
        Log.i("","........start......"+tag_menu_start_position+"...end..."+tag_menu_end_position);
        return new DeleteBillTagBean(
                billList.get(click_Item),
                click_Item,
                tag_menu_start_position,
                tag_menu_end_position);

    }
}
