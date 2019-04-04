package com.meng.mengtestapplication.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.adapter.RecyclerViewSlidingAdapter;
import com.meng.mengtestapplication.util.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

public class TestRvSlidingActivity extends AppCompatActivity implements RecyclerViewSlidingAdapter.onSlidingViewClickListener {
    private RecyclerView recycler;
    private RecyclerViewSlidingAdapter rvAdapter;

    private List<Bitmap> dataImage;
    private List<String> dataTitle;
    private List<String> dataContent;
    private List<String> dataTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rv_sliding);
        recycler = findViewById(R.id.rv_sliding);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        datas();
        updateInterface();
    }

    private void updateInterface() {
        if (rvAdapter == null) {
            rvAdapter = new RecyclerViewSlidingAdapter(this, dataImage, dataTitle, dataContent, dataTime);
            recycler.setAdapter(rvAdapter);
            recycler.setItemAnimator(new DefaultItemAnimator());
        } else {
            rvAdapter.notifyDataSetChanged();
        }
        rvAdapter.setOnSlidingListener(this);
    }

    private void datas() {
        dataImage = new ArrayList<Bitmap>();    //头像（谁的头像）
        dataTitle = new ArrayList<String>();     //标题（谁的消息）
        dataContent = new ArrayList<String>();  //内容（消息内容）
        dataTime = new ArrayList<String>();     //时间（消息时间）
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_buildingmaterial, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_tobacco, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_metallurgy, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_coal_mine, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_coloured, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_buildingmaterial, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_tobacco, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_metallurgy, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_coal_mine, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_coloured, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_buildingmaterial, this)));
        dataImage.add(RecyclerUtils.toRoundBitmap(RecyclerUtils.bitmaps(R.mipmap.iv_tobacco, this)));
        dataTitle.add("Android开发交流群");
        dataTitle.add("R语言初级入门学习");
        dataTitle.add("刘亦菲");
        dataTitle.add("策划书交流群");
        dataTitle.add("15生态宜居学院学生群");
        dataTitle.add("湘环资助 （助学贷款）");
        dataTitle.add("湘环编程研讨会");
        dataTitle.add("丰风");
        dataTitle.add("阿娇");
        dataTitle.add("图书馆流通服务交流群");
        dataTitle.add("one3胡了");
        dataTitle.add("读者协会策划部");
        dataContent.add("广州_Even：[图片]");
        dataContent.add("轻舟飘飘：auto基本不准");
        dataContent.add("不会的");
        dataContent.add("残留的余温。：分享[熊猫直播]");
        dataContent.add("刘老师：[文件]2018年6月全国大学……");
        dataContent.add("17级园林");
        dataContent.add("黄晓明：20k不到");
        dataContent.add("[文件]编程之美");
        dataContent.add("i5的处理器，比较稳定，蛮好的");
        dataContent.add("寥寥：好的，谢谢老师");
        dataContent.add("易天：阿龙还在面试呢");
        dataContent.add("策划部陈若依、：请大家把备注改好");
        dataTime.add("16:24");
        dataTime.add("14:37");
        dataTime.add("10:58");
        dataTime.add("昨天");
        dataTime.add("昨天");
        dataTime.add("昨天");
        dataTime.add("星期三");
        dataTime.add("星期三");
        dataTime.add("星期二");
        dataTime.add("星期二");
        dataTime.add("星期二");
        dataTime.add("星期一");

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "点击了" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteBtnClick(View view, int position) {
        rvAdapter.removeData(position);
    }
}
