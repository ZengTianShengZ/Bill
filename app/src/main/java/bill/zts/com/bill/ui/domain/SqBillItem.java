package bill.zts.com.bill.ui.domain;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class SqBillItem extends DataSupport {

    private SqBill sqBill;
    private String strMoney;
    private List<String> tagList;

    public SqBill getSqBill() {
        return sqBill;
    }

    public void setSqBill(SqBill sqBill) {
        this.sqBill = sqBill;
    }

    public String getStrMoney() {
        return strMoney;
    }

    public void setStrMoney(String strMoney) {
        this.strMoney = strMoney;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
