package bill.zts.com.bill.ui.domain;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class SqBill extends DataSupport {

    private String testStr;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    private int id;
    @Column(unique = true, defaultValue = "0")
    private String totalMoney;
    private int intDay;
    private List<SqBillItem> billList = new ArrayList<SqBillItem>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SqBillItem> getBillList() {
        return billList;
    }

    public void setBillList(List<SqBillItem> billList) {
        this.billList = billList;
    }

    public int getIntDay() {
        return intDay;
    }

    public void setIntDay(int intDay) {
        this.intDay = intDay;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
