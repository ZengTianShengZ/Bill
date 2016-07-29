package bill.zts.com.bill.ui.domain;

/**
 * Created by Administrator on 2016/7/29.
 */
public class DeleteBillBean {

    private int delete_position;
    private AddBillBean addBillBean;

    public DeleteBillBean(int delete_position, AddBillBean addBillBean) {

        this.addBillBean = addBillBean;
        this.delete_position = delete_position;
    }

    public AddBillBean getAddBillBean() {
        return addBillBean;
    }

    public void setAddBillBean(AddBillBean addBillBean) {
        this.addBillBean = addBillBean;
    }

    public int getDelete_position() {
        return delete_position;
    }

    public void setDelete_position(int delete_position) {
        this.delete_position = delete_position;
    }
}
