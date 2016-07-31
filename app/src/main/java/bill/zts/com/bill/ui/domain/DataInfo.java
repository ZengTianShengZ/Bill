package bill.zts.com.bill.ui.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class DataInfo {

    private int intData;
    private String totalMoney;
    private String monthInfo;
    private String weekInfo;
    private String dayInfo;
    private List<AddBillBean> billList = new ArrayList<AddBillBean>();

    public DataInfo(String monthInfo, String weekInfo, String dayInfo,String totalMoney,int intData) {
        this.monthInfo = monthInfo;
        this.weekInfo = weekInfo;
        this.dayInfo = dayInfo;
        this.totalMoney = totalMoney;
        this.intData = intData;
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getMonthInfo() {
        return monthInfo;
    }

    public void setMonthInfo(String monthInfo) {
        this.monthInfo = monthInfo;
    }

    public String getDayInfo() {
        return dayInfo;
    }

    public void setDayInfo(String dayInfo) {
        this.dayInfo = dayInfo;
    }

    public String getWeekInfo() {
        return weekInfo;
    }

    public void setWeekInfo(String weekInfo) {
        this.weekInfo = weekInfo;
    }

    public List<AddBillBean> getBillList() {
        return billList;
    }

    public void setBillList(List<AddBillBean> billList) {
        this.billList = billList;
    }
}
