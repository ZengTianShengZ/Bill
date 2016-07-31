package bill.zts.com.bill.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/7/31.
 */
public class SQMoneyTag extends DataSupport {

    private int id;
    private int sqBillId;

    private String moneyTag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMoneyTag() {
        return moneyTag;
    }

    public void setMoneyTag(String moneyTag) {
        this.moneyTag = moneyTag;
    }

    public int getSqBillId() {
        return sqBillId;
    }

    public void setSqBillId(int sqBillId) {
        this.sqBillId = sqBillId;
    }
}
