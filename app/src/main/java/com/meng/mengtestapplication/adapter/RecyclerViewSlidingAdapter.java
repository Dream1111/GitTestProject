package com.meng.mengtestapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.util.RecyclerUtils;
import com.meng.mengtestapplication.view.RecyclerItemView;

import java.util.List;

/**
 * Created by Administrator on 2019/4/4.
 */

public class RecyclerViewSlidingAdapter extends RecyclerView.Adapter<RecyclerViewSlidingAdapter.SimpleHolder> implements RecyclerItemView.onSlidingButtonListener {

    private Context context;
    private List<Bitmap> dataImage;
    private List<String> dataTitle;
    private List<String> dataContent;
    private List<String> dataTime;

    private RecyclerItemView recyclers;
    private onSlidingViewClickListener listener;

    public RecyclerViewSlidingAdapter(Context context) {
        this.context = context;
    }

    public RecyclerViewSlidingAdapter(Context context, List<Bitmap> dataImage, List<String> dataTitle, List<String> dataContent, List<String> dataTime) {
        this.context = context;
        this.dataImage = dataImage;
        this.dataTitle = dataTitle;
        this.dataContent = dataContent;
        this.dataTime = dataTime;
    }

    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_test_rv_sliding, parent, false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleHolder holder, int position) {
        holder.image.setImageBitmap(dataImage.get(position));
        holder.title.setText(dataTitle.get(position));
        holder.content.setText(dataContent.get(position));
        holder.time.setText(dataTime.get(position));
        holder.layout_left.getLayoutParams().width = RecyclerUtils.getScreenWidth(context);

        holder.layout_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    //获得布局下标
                    int subscrip = holder.getLayoutPosition();
                    listener.onItemClick(v, subscrip);
                }
            }
        });

        holder.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "其他", Toast.LENGTH_SHORT).show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "删除", Toast.LENGTH_SHORT).show();
                int subscript = holder.getLayoutPosition();
                listener.onDeleteBtnClick(v, subscript);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataImage.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        recyclers = (RecyclerItemView) view;
    }

    @Override
    public void onDownOrMove(RecyclerItemView recycler) {
        if (menuIsOpen()) {
            if (recycler != recycler) {
                closeMenu();
            }
        }
    }

    //删除数据
    public void removeData(int position) {
        dataImage.remove(position);
        notifyItemRemoved(position);
    }

    //关闭菜单
    private void closeMenu() {
        recyclers.closeMenu();
        recyclers = null;
    }

    //判断是否有菜单打开
    public boolean menuIsOpen() {
        if (recyclers != null) {
            return true;
        }
        return false;
    }

    //设置在滑动侦听器上
    public void setOnSlidingListener(onSlidingViewClickListener listener) {
        this.listener = listener;
    }

    //在滑动视图上单击侦听器
    public interface onSlidingViewClickListener {
        void onItemClick(View view, int position);

        void onDeleteBtnClick(View view, int position);
    }

    class SimpleHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView content;
        public TextView time;
        public TextView other;
        public TextView delete;
        public LinearLayout layout_left;

        public SimpleHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_icon);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            time = itemView.findViewById(R.id.tv_time);
            other = itemView.findViewById(R.id.tv_other);
            delete = itemView.findViewById(R.id.tv_delete);
            layout_left = itemView.findViewById(R.id.ll_left);
            ((RecyclerItemView)itemView).setSlidingButtonListener(RecyclerViewSlidingAdapter.this);
        }
    }
}
