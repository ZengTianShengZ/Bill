package bill.zts.com.bill.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/7/28.
 */
public class ClashLinearLayout extends LinearLayout {
    public ClashLinearLayout(Context context) {
        super(context);
    }

    public ClashLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClashLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
