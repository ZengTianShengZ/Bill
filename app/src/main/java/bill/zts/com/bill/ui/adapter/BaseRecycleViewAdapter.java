package bill.zts.com.bill.ui.adapter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  RecyclerView.Adapter  基类
 *
 * @ClassName: BaseRecycleViewAdapter
 * @Description: TODO
 * @author zss
 * @date 2016-4-29 PM
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {


	protected Context mContext;
	protected int mLayoutId;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;


	public BaseRecycleViewAdapter(Context context, int layoutId, List<T> datas) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayoutId = layoutId;
		mDatas = datas;
	}

	@Override
	public int getItemCount() {
		return mDatas.size() == 0 ? 0 : mDatas.size();
	}


	@Override
	public void onBindViewHolder(final RecycleViewHolder holder, final int position) {
		holder.updatePosition(position);
		convert(holder, mDatas.get(position), holder.getAdapterPosition());

	}

	@Override
	public RecycleViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

		final RecycleViewHolder viewHolder = RecycleViewHolder.get(mContext, parent, mLayoutId);
		return viewHolder;

	}

	public abstract void convert(RecycleViewHolder holder, T t, int holderPosition);

	/**
	 * 添加一条数据
     */
	public void insertedItem(T t){
		mDatas.add(t);
		notifyItemInserted(mDatas.size());
	}
	public void insertedAllItem(List<T> data){
		mDatas.addAll(data);
		notifyDataSetChanged();
	}
	public void appendMoreItem(List<T> data){
		mDatas.addAll(0,data);
		notifyDataSetChanged();
	}
	/**
	 * 移除一条数据
	 * @param position
     */
	public void removePosition(int position){
		if(position >=0) {
			mDatas.remove(position);
			notifyItemRemoved(position);
			notifyDataSetChanged();
		}
	}


}
