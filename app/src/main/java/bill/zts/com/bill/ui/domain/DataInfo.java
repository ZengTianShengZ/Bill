package bill.zts.com.bill.ui.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class DataInfo {

    private String monthInfo;
    private String weekInfo;
    private String dataInfo;
    private List<AddBillBean> billList = new ArrayList<AddBillBean>();

    public DataInfo(String monthInfo, String weekInfo, String dataInfo) {
        this.monthInfo = monthInfo;
        this.weekInfo = weekInfo;
        this.dataInfo = dataInfo;
    }

    public String getMonthInfo() {
        return monthInfo;
    }

    public void setMonthInfo(String monthInfo) {
        this.monthInfo = monthInfo;
    }

    public String getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(String dataInfo) {
        this.dataInfo = dataInfo;
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
