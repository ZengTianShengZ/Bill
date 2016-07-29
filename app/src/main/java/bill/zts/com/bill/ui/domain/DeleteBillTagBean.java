package bill.zts.com.bill.ui.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class DeleteBillTagBean {

    private int delete_moneyTag_position;
    private int delete_menuTag_start_position;
    private int delete_menuTag_end_position;
    private AddBillBean addBillBean;

    public DeleteBillTagBean(AddBillBean addBillBean, int delete_moneyTag_position , int delete_menuTag_start_position,int delete_menuTag_end_position) {
        this.addBillBean = addBillBean;
        this.delete_menuTag_end_position = delete_menuTag_end_position;
        this.delete_menuTag_start_position = delete_menuTag_start_position;
        this.delete_moneyTag_position = delete_moneyTag_position;
    }

    public AddBillBean getAddBillBean() {
        return addBillBean;
    }

    public void setAddBillBean(AddBillBean addBillBean) {
        this.addBillBean = addBillBean;
    }

    public int getDelete_menuTag_end_position() {
        return delete_menuTag_end_position;
    }

    public void setDelete_menuTag_end_position(int delete_menuTag_end_position) {
        this.delete_menuTag_end_position = delete_menuTag_end_position;
    }

    public int getDelete_menuTag_start_position() {
        return delete_menuTag_start_position;
    }

    public void setDelete_menuTag_start_position(int delete_menuTag_start_position) {
        this.delete_menuTag_start_position = delete_menuTag_start_position;
    }

    public int getDelete_moneyTag_position() {
        return delete_moneyTag_position;
    }

    public void setDelete_moneyTag_position(int delete_moneyTag_position) {
        this.delete_moneyTag_position = delete_moneyTag_position;
    }
}
