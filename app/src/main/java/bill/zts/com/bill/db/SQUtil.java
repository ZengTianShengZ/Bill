package bill.zts.com.bill.db;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bill.zts.com.bill.ui.domain.AddBillBean;
import bill.zts.com.bill.ui.domain.DataInfo;
import bill.zts.com.bill.utils.DataUtils;

/**
 * Created by Administrator on 2016/7/31.
 */
public class SQUtil {

    public static void setDataBean(int intData, List<AddBillBean> billList){

        List<SQBill> sqBills = DataSupport.where("intDay = ?",intData+"").find(SQBill.class);

        int sqBillId;
        if(0 == sqBills.size()){
            SQBill newSQBill = new SQBill();
            newSQBill.setIntDay(intData);
            newSQBill.save();
            //只有 save 才能 getID ， upData 的 getIDea 是为空的。
            sqBillId = newSQBill.getId();
        }else{
            sqBillId = sqBills.get(0).getId();
        }
        Log.i("...","........sqBillId............."+sqBillId);

        saveListBilltag(sqBillId,billList);

       /* for(AddBillBean addBillBean:billList){

            SQMoneyTag sqMoneyTag = new SQMoneyTag();

            sqMoneyTag.setMoneyTag(addBillBean.getStrMoney());
            sqMoneyTag.setSqBillId(sqBillId);
            sqMoneyTag.save();

            Log.i("...","........sqMoneyTag.............");

            List<SQMenuTag> sqMenuTags = new ArrayList<SQMenuTag>();
            for(String str:addBillBean.getTagList()){
                SQMenuTag sqMenuTag = new SQMenuTag();
                sqMenuTag.setMenuTag(str);
                sqMenuTag.setSqBillId(sqBillId);
                sqMenuTag.setSqMoneyId(sqMoneyTag.getId());
                sqMenuTags.add(sqMenuTag);

                Log.i("...","........sqMenuTags.............");
            }
            DataSupport.saveAll(sqMenuTags);
        }
*/
        Log.i("...","........yyyy.............");

    }

    public static void findDataBean(int int_day, DataInfo dataInfo){

        List<SQBill> sqBills = DataSupport.where("intDay = ?",int_day+"").find(SQBill.class);
        if(0 != sqBills.size()){
            SQBill sqBill =  sqBills.get(0);
            Log.i("...","........sqBill ....getId........."+sqBill.getId());

            List<SQMoneyTag> sqMoneyTags = DataSupport.where("sqBillId = ?",sqBill.getId()+"").find(SQMoneyTag.class);
            if(0 != sqMoneyTags.size()){

                List<AddBillBean> addBillBeens = new ArrayList<AddBillBean>();

                float totalMoneys = 0;
                for(SQMoneyTag sqMoneyTag:sqMoneyTags){

                    AddBillBean addBillBean = new AddBillBean();
                    addBillBean.setStrMoney(sqMoneyTag.getMoneyTag());
                    // 计算 钱 总数
                    Float float_money = new Float(sqMoneyTag.getMoneyTag());
                    totalMoneys += float_money;

                    Log.i("..","......sqMoneyTag.....getMoneyTag............"+   sqMoneyTag.getMoneyTag());

                    List<SQMenuTag> sqMenuTags = DataSupport.where("sqBillId = ? and sqMoneyId = ?",sqBill.getId()+"",sqMoneyTag.getId()+"").find(SQMenuTag.class);
                    if(0 != sqMenuTags.size()){

                        List<String> monuStrList = new ArrayList<String>();

                        for(SQMenuTag sqMenuTag:sqMenuTags){
                            monuStrList.add(sqMenuTag.getMenuTag());
                            Log.i("..","......sqMenuTag.....getMenuTag......."+  sqMenuTag.getMenuTag());
                        }
                        addBillBean.setTagList(monuStrList);
                    }

                    addBillBeens.add(addBillBean);
                }

                dataInfo.setTotalMoney(totalMoneys+"");
                dataInfo.setBillList(addBillBeens);
            }

        }
        Log.i("..","....findDataBean....eeee......." );
    }

    public static void deleteDataBean(int sqBillDay ,List<AddBillBean> billList){
        List<SQBill> sqBills = DataSupport.where("intDay = ?",sqBillDay+"").find(SQBill.class);
        if(0 != sqBills.size()) {
            SQBill sqBill = sqBills.get(0);
            Log.i("..","....findDataBean....ddddd......." );
            DataSupport.deleteAll(SQMoneyTag.class, "sqBillId = ? ",sqBill.getId()+"");

            DataSupport.deleteAll(SQMenuTag.class, "sqBillId = ? ",sqBill.getId()+"");

            // 全部删除后 再重新 赋值， 这是我想到的比较简便省事的方法
            saveListBilltag(sqBill.getId(),billList);
            Log.i("..","....findDataBean....dddd  vvvv......." );

        }
    }

    private static void saveListBilltag(int sqBillId ,List<AddBillBean> billList){

        for(AddBillBean addBillBean:billList){

            SQMoneyTag sqMoneyTag = new SQMoneyTag();

            sqMoneyTag.setMoneyTag(addBillBean.getStrMoney());
            sqMoneyTag.setSqBillId(sqBillId);
            sqMoneyTag.save();

            Log.i("...","........sqMoneyTag.............");

            List<SQMenuTag> sqMenuTags = new ArrayList<SQMenuTag>();
            for(String str:addBillBean.getTagList()){
                SQMenuTag sqMenuTag = new SQMenuTag();
                sqMenuTag.setMenuTag(str);
                sqMenuTag.setSqBillId(sqBillId);
                sqMenuTag.setSqMoneyId(sqMoneyTag.getId());
                sqMenuTags.add(sqMenuTag);

                Log.i("...","........sqMenuTags.............");
            }
            DataSupport.saveAll(sqMenuTags);
        }
    }

    public static DataInfo findThisDayData(String str_day){

        String strDay = str_day.replaceAll("-","");
        int int_day = Integer.parseInt(strDay);

        DataInfo dataInfo  = new DataInfo();

        List<SQBill> sqBills = DataSupport.where("intDay = ?",int_day+"").find(SQBill.class);
        if(0 != sqBills.size()){

            SQBill sqBill =  sqBills.get(0);

            List<SQMoneyTag> sqMoneyTags = DataSupport.where("sqBillId = ?",sqBill.getId()+"").find(SQMoneyTag.class);
            if(0 != sqMoneyTags.size()){

                List<AddBillBean> addBillBeens = new ArrayList<AddBillBean>();

                float totalMoneys = 0;
                for(SQMoneyTag sqMoneyTag:sqMoneyTags){

                    AddBillBean addBillBean = new AddBillBean();
                    addBillBean.setStrMoney(sqMoneyTag.getMoneyTag());
                    // 计算 钱 总数
                    Float float_money = new Float(sqMoneyTag.getMoneyTag());
                    totalMoneys += float_money;

                    List<SQMenuTag> sqMenuTags = DataSupport.where("sqBillId = ? and sqMoneyId = ?",sqBill.getId()+"",sqMoneyTag.getId()+"").find(SQMenuTag.class);
                    if(0 != sqMenuTags.size()){

                        List<String> monuStrList = new ArrayList<String>();

                        for(SQMenuTag sqMenuTag:sqMenuTags){
                            monuStrList.add(sqMenuTag.getMenuTag());

                        }
                        addBillBean.setTagList(monuStrList);
                    }

                    addBillBeens.add(addBillBean);
                }

                dataInfo.setTotalMoney(totalMoneys+"");
                dataInfo.setBillList(addBillBeens);
                dataInfo.setWeekInfo(strWeek(str_day));
                dataInfo.setMonthInfo(str_day);
            }

        }
        return  dataInfo;

    }

    private static String strWeek(String str_day){
        String a[] = str_day.split("-");
        String year = a[0];
        String month = a[1];
        String day = a[2];
        Calendar c= Calendar.getInstance();//获取当前日期
        Log.i("...","................."+year+".."+month+"..."+day);
        Log.i("...","................."+year+".."+month+"..."+day);
        Log.i("...","................."+year+".."+month+"..."+day);
        Log.i("...","................."+year+".."+month+"..."+day);
        c.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
        //int week1 = c.get(Calendar.DAY_OF_WEEK);
        return DataUtils.weekDays[c.get(Calendar.DAY_OF_WEEK)-1];
    }
}








