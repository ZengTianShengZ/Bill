package bill.zts.com.bill.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/7/31.
 */
public class SQMenuTag extends DataSupport {

    private int id;
    private String menuTag;
    private int sqBillId;
    private int sqMoneyId;

    public int getSqBillId() {
        return sqBillId;
    }

    public void setSqBillId(int sqBillId) {
        this.sqBillId = sqBillId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuTag() {
        return menuTag;
    }

    public void setMenuTag(String menuTag) {
        this.menuTag = menuTag;
    }

    public int getSqMoneyId() {
        return sqMoneyId;
    }

    public void setSqMoneyId(int sqMoneyId) {
        this.sqMoneyId = sqMoneyId;
    }
}
