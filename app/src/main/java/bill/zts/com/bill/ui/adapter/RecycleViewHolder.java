package bill.zts.com.bill.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecycleViewHolder extends RecyclerView.ViewHolder{

	private SparseArray<View> mViews;
    public View mConvertView;
    private Context mContext;
    
	public RecycleViewHolder(Context context, View itemView, ViewGroup parent) {
		super(itemView);

		mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
        
	}
	
	public static RecycleViewHolder get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        RecycleViewHolder holder = new RecycleViewHolder(context, itemView, parent);
        return holder;
    }
	
	/**
     * ͨ��viewId��ȡ�ؼ�
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    
    public RecycleViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RecycleViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

	public void updatePosition(int position) {
		 
	}
    

}
