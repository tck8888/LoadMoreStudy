package com.tck.loadmorestudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.tck.loadmorestudy.InfoAdapter;
import com.tck.loadmorestudy.R;
import com.tck.loadmorestudy.bean.InfoBean;
import com.tck.loadmorestudy.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class PrimaryFragment extends Fragment {

    private List<InfoBean> mDataList = new ArrayList<>();
    private Handler mHandler;
    private InfoAdapter mInfoAdapter;

    {
        for (int i = 0; i < 5; i++) {
            mDataList.add(new InfoBean(R.mipmap.icon_lion, R.string.test1));
        }
    }

    @LayoutRes
    int sampleLayoutRes;

    public static PrimaryFragment newInstance(@LayoutRes int sampleLayoutRes) {
        PrimaryFragment fragment = new PrimaryFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(sampleLayoutRes);
        sampleStub.inflate();


        mHandler = new Handler();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mInfoAdapter = new InfoAdapter(getContext(), mDataList);
        recyclerView.setAdapter(mInfoAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = recyclerView.getAdapter().getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//最后一个可见
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();//第一个可见
                int childCount = recyclerView.getChildCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE//滑动停止
                        && lastVisibleItemPosition == itemCount - 1//position从0开始索引
                        && childCount > 0) {
                    //加载更多
                    loadMoreData();
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE && firstVisibleItemPosition == 0) {
                    //刷新数据
                    refreshData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return view;
    }

    /**
     * description: 刷新数据
     *
     * @author TCK
     * created at 2017/11/3 0003
     */
    private void refreshData() {
        mDataList.clear();
        initData();
    }

    /**
     * description: 加载更多
     *
     * @author TCK
     * created at 2017/11/3 0003
     */
    private void loadMoreData() {
        mHandler.postDelayed(new Runnable() {
            /**
             *
             */
            @Override
            public void run() {
                initData();
            }


        }, 2000);
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            mDataList.add(new InfoBean(R.mipmap.icon_lion, R.string.test1));
        }
        mInfoAdapter.notifyDataSetChanged();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            sampleLayoutRes = arguments.getInt("sampleLayoutRes");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
