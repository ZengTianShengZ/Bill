package bill.zts.com.bill.ui.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class AddBillBean {

    private String strMoney;
    private List<String> tagList;

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
