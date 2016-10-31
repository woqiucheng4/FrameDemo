package qc.com.framedemo.view.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import qc.com.framedemo.R;
import qc.com.framedemo.bean.Page;
import qc.com.framedemo.view.BaseFragment;
import qc.com.framedemo.view.adapter.SampleAdapter;
import qc.com.framedemo.view.widget.DividerItemDecoration;
import qc.com.framedemo.view.widget.SwipeRecyclerView;

/**
 * <ul>
 * <li>功能职责：FirstFragment</li>
 * </ul>
 *
 * @author chengqiu
 * @date 2016-08-08
 */

public class FirstFragment extends BaseFragment implements SwipeRecyclerView.RefreshLoadMoreListener {

    @Bind(R.id.swipeRecyclerView)
    public SwipeRecyclerView mSwipeRecyclerView;

    private List<Integer> list = new ArrayList<>();

    private SampleAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int size = list.size();
                    List<Integer> templist = new ArrayList<>();
                    templist.add(size);
                    Page page = new Page();
                    page.setNowPage(3);
                    page.setTotalPage(3);
                    mSwipeRecyclerView.updateRecyclerViewData(page, templist);
                    break;
                case 2:
                    List<Integer> refreshlist = new ArrayList<>();
                    for (int i = 20; i > 0; i--) {
                        refreshlist.add(i);
                    }
                    Page pageRefresh = new Page();
                    pageRefresh.setNowPage(1);
                    pageRefresh.setTotalPage(3);
                    mSwipeRecyclerView.updateRecyclerViewData(pageRefresh, refreshlist);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void initRootView(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_first, null, false);
    }

    @Override
    protected void initViews() {
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        adapter = new SampleAdapter(getActivity(), list);
        mSwipeRecyclerView.setAdapter(adapter);
        mSwipeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mSwipeRecyclerView.setRefreshLoadMoreListener(this);
    }

    @Override
    public void onRefresh() {
        Message msg = handler.obtainMessage();
        msg.what = 2;
        handler.sendMessageDelayed(msg, 3000);
    }

    @Override
    public void onLoadMore() {
        Message msg = handler.obtainMessage();
        msg.what = 1;
        handler.sendMessageDelayed(msg, 3000);
    }
}
