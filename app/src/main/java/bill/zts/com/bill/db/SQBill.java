package bill.zts.com.bill.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/7/31.
 */
public class SQBill  extends DataSupport {

    private int id;
    private int intDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntDay() {
        return intDay;
    }

    public void setIntDay(int intDay) {
        this.intDay = intDay;
    }
}
