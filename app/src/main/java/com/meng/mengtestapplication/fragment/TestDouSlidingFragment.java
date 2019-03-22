package com.meng.mengtestapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.base.BaseRecAdapter;
import com.meng.mengtestapplication.base.BaseRecViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/3/22.
 * RecyclerView实现滑动（仿抖音视频滑动效果）
 */

public class TestDouSlidingFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;

    private List<Integer> integerList;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private LisDouAdapter lisDouAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_test_dou1, container, false);
            recyclerView = view.findViewById(R.id.rv_pager);
            /**
             * 给颜色值（随机数形式）
             */
            integerList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                int red = new Random().nextInt(255);
                int green = new Random().nextInt(255);
                int blue = new Random().nextInt(255);
                integerList.add(Color.argb(50, red, green, blue));
            }

            snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);

            lisDouAdapter = new LisDouAdapter(integerList);
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(lisDouAdapter);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    switch (newState) {
                        case RecyclerView.SCROLL_STATE_IDLE://停止滚动(停止滑动的时候应该加载视频)
                            break;
                        case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                            break;
                        case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                            break;
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }
        return view;
    }

    class LisDouAdapter extends BaseRecAdapter<Integer, ViewHolder> {

        public LisDouAdapter(List<Integer> list) {
            super(list);
        }

        @Override
        public void onHolder(ViewHolder holder, Integer bean, int position) {
            //布局高度设置为match_parent
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

            holder.tv_test.setBackgroundColor(bean);

            holder.tv_test.setText("第" + position + "个");
        }

        @Override
        public ViewHolder onCreateHolder() {
            return new ViewHolder(getViewByRes(R.layout.item_recycle_dou));
        }
    }

    public class ViewHolder extends BaseRecViewHolder {
        private View rootView;
        private TextView tv_test;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            tv_test = itemView.findViewById(R.id.tv_test);
        }
    }
}
