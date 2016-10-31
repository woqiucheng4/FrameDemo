package qc.com.framedemo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.List;

import qc.com.framedemo.R;

/**
 * <ul>
 * <li>功能职责：</li>
 * </ul>
 *
 * @author chengqiu
 * @date 2016-08-26
 */
public class SampleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Integer> list;
    private Context mContext;
    private boolean mEnableLoadMore;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public List<Integer> getData() {
        return list;
    }

    public void setData(List<Integer> list) {
        this.list = list;
    }

    public SampleAdapter(Context context, List<Integer> list) {
        mContext = context;
        this.list = list;
        mEnableLoadMore = false;
    }

    // RecyclerView的count设置为数据总条数+ 1（footerView）
    @Override
    public int getItemCount() {
        return mEnableLoadMore ? list.size() + 1 : list.size();

    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (mEnableLoadMore && position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).textView.setText(String.valueOf(list.get(position)));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_first_fragment, null);
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view);
        }
        // type == TYPE_FOOTER 返回footerView
        else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_footer_view, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }

        return null;
    }

    class FooterViewHolder extends ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    class ItemViewHolder extends ViewHolder {
        TextView textView;

        public ItemViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }

    public void setEnableLoadMore(boolean enable) {
        mEnableLoadMore = enable;
    }
}