package bill.zts.com.bill.ui.adapter;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bill.zts.com.bill.R;
import bill.zts.com.bill.ui.domain.DataInfo;


/**
 *  RecyclerView.Adapter  基类
 *
 * @ClassName: BaseRecycleViewAdapter
 * @Description: TODO
 * @author zss
 * @date 2016-4-29 PM
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {

	//  头部
	private final int TYPE_HEAD = 1;
	// item
	private final int TYPE_BODY = 2;
	// 尾部
	private final int TYPE_BOTTOM = 3;

	protected Context mContext;
	protected int mLayoutId;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected Map<Integer,Integer> monthMap = new HashMap<>();

	public static final int LAST_POSITION = -1;

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
	public long getItemId(int position) {
		return position;
	}

 	@Override
	public int getItemViewType(int position) {
		/*if(position == 0){
			return TYPE_HEAD;
		}*/
		if(monthMap.get(getItemCount())!=null){
			Log.i("monthMap","..........monthMap.........."+monthMap.get(getItemCount()));
			Log.i("position","..........position.........."+position);
			if(position+1 == monthMap.get(getItemCount())){
				return TYPE_HEAD;
			}
		}
		if (position+1  == getItemCount()) {
			monthMap.put(getItemCount(),getItemCount());
			return TYPE_BOTTOM;
		} else {
			return TYPE_BODY;
		}
	}

	@Override
	public void onBindViewHolder(final RecycleViewHolder holder, final int position) {

		int itemType = getItemViewType(position);
		switch (itemType) {
			case TYPE_HEAD:
				bindHead(holder, mDatas.get(position), holder.getAdapterPosition());
				break;
			case TYPE_BODY:
				bindBody(holder, mDatas.get(position), holder.getAdapterPosition());
				break;
			case TYPE_BOTTOM:
				bindBottom(holder, mDatas.get(position), holder.getAdapterPosition());
				break;
			default:
				break;
		}


		//holder.updatePosition(position);
		//bindBody(holder, mDatas.get(position), holder.getAdapterPosition());
		/*if(position + 1 == getItemCount()){
			TextView lodingTv = holder.getView(R.id.tv_loading);
			lodingTv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(mOnLoadingListener!=null){
						mOnLoadingListener.loading(holder.getView(R.id.progress_loading));
					}
				}
			});
		}else {
			convert(holder, mDatas.get(position), holder.getPosition());
		}*/
	}

	@Override
	public RecycleViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
		switch (viewType) {
			case TYPE_HEAD:
				return  RecycleViewHolder.get(mContext, parent, R.layout.list_item_month_hrad);
			case TYPE_BODY:
				return RecycleViewHolder.get(mContext, parent, mLayoutId);

			case TYPE_BOTTOM:
				return  RecycleViewHolder.get(mContext, parent, R.layout.bottom_reflash_layout);

		}
		return null;
		/*if(viewType == TYPE_NORMAL_ITEM){
			final RecycleViewHolder viewHolder = RecycleViewHolder.get(mContext, parent, mLayoutId);
			return viewHolder;
		}else{
			final RecycleViewHolder loadingViewHolder = RecycleViewHolder.get(mContext, parent, R.layout.loading_layout);
			return loadingViewHolder;
		} //
		final RecycleViewHolder viewHolder = RecycleViewHolder.get(mContext, parent, mLayoutId);
		return viewHolder;*/

	}

	public abstract void bindBody(RecycleViewHolder holder, T t, int holderPosition);
	public abstract void bindHead(RecycleViewHolder holder, T t, int holderPosition);
	public abstract void bindBottom(RecycleViewHolder holder, T t, int holderPosition);
	/**
	 * 添加一条数据
	 * @param t
	 * @param position
     */
	public void insertedItem(T t,int position){
		mDatas.add(position,t);
		notifyItemInserted(position);
	}
	public void insertedAllItem(List<T> data){
		mDatas.addAll(data);
		notifyDataSetChanged();
	}
	public void appendMoreItem(List<T> data){
		mDatas.addAll(mDatas.size()-1,data);

		//Cannot call this method while RecyclerView is computing a layout or scrolling
		// 直接 notifyDataSetChanged 会 抛异常，因为 在 执行 onBindViewHolder 不能 notifyDataSetChanged
		Handler handler = new Handler(mContext.getMainLooper());
		final Runnable r = new Runnable() {
			public void run() {
			    notifyDataSetChanged();
			}
		};
		handler.post(r);
		//notifyDataSetChanged();
	}
	/**
	 * 移除一条数据
	 * @param position
     */
	public void removePosition(int position){
		if(position >=0) {
			mDatas.remove(position);
			notifyItemRemoved(position);

		}
	}


}
