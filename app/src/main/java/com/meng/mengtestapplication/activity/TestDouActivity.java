package com.meng.mengtestapplication.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.base.BaseRecAdapter;
import com.meng.mengtestapplication.base.BaseRecViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDouActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Integer> integerList;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private ListDouAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dou);
        recyclerView = findViewById(R.id.rv_pager);

        integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int red = new Random().nextInt(255);
            int green = new Random().nextInt(255);
            int blue = new Random().nextInt(255);

            integerList.add(Color.argb(100, red, green, blue));
        }

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        layoutManager = new LinearLayoutManager(TestDouActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListDouAdapter(integerList);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
//                        View view = snapHelper.findSnapView(layoutManager);
//                        JZVideoPlayer.releaseAllVideos();
//                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
//                        if (viewHolder != null && viewHolder instanceof VideoViewHolder) {
//                            ((VideoViewHolder) viewHolder).mp_video.startVideo();
//                        }

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });

    }


    class ListDouAdapter extends BaseRecAdapter<Integer, ViewHolder> {
        public ListDouAdapter(List<Integer> list) {
            super(list);
        }

        @Override
        public void onHolder(ViewHolder holder, Integer bean, int position) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

            holder.tv.setBackgroundColor(bean);
            holder.tv.setText("第" + position + "个视频");
        }

        @Override
        public ViewHolder onCreateHolder() {
            return new ViewHolder(getViewByRes(R.layout.item_recycle_dou));
        }
    }

    class ViewHolder extends BaseRecViewHolder{
        private View rooView;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rooView=itemView;
            this.tv=rooView.findViewById(R.id.tv_test);

        }
    }
}
