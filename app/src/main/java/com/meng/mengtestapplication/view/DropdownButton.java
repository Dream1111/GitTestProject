package com.meng.mengtestapplication.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meng.mengtestapplication.R;
import com.meng.mengtestapplication.bean.DropBean;
import com.meng.mengtestapplication.util.PopWinDownUtil;

import java.util.List;

/**
 * Created by Administrator on 2019/3/25.
 */

public class DropdownButton extends RelativeLayout implements Checkable, View.OnClickListener, AdapterView.OnItemClickListener, PopWinDownUtil.OnDismissListener {
    //菜单按钮文字内容
    private TextView text;
    //菜单按钮底部的提示条
    private View bLine;
    private boolean isCheced;
    private PopWinDownUtil popWinDownUtil;
    private Context mContext;
    private List<DropBean> drops;
    private int selectPostion;
    private DropDownAdapter adapter;

    public DropdownButton(Context context) {
        this(context, null);
    }

    public DropdownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化布局
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        //菜单按钮的布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dropdown_tab_button, this, true);
        text = view.findViewById(R.id.tv_text_title);
        bLine = view.findViewById(R.id.bottomLine);

        Drawable icon = getResources().getDrawable(R.mipmap.iv_drop_gray_small);
        text.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
        //点击事件，点击外部区域隐藏popupwindow
        setOnClickListener(this);

    }

    public void setData(List<DropBean> dropBeans) {
        if (dropBeans == null || dropBeans.isEmpty()) {
            return;
        }

        drops = dropBeans;
        drops.get(0).setChoiced(true);
        text.setText(drops.get(0).getText());
        selectPostion = 0;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dropdown_content, null);
        view.findViewById(R.id.view_transparent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popWinDownUtil.hide();
            }
        });

        ListView listView = view.findViewById(R.id.lv_data_dropdown);
        listView.setOnItemClickListener(this);

        adapter = new DropDownAdapter(drops, mContext);
        listView.setAdapter(adapter);

        popWinDownUtil = new PopWinDownUtil(mContext, view, this);
        popWinDownUtil.setOnDismissListener(this);
    }

    public void setText(CharSequence content) {
        text.setText(content);
    }

    @Override
    public void setChecked(boolean checked) {
        isCheced = checked;
        Drawable icon;
        if (checked) {
            icon = getResources().getDrawable(R.mipmap.iv_up_blue);
            text.setTextColor(getResources().getColor(R.color.bgColor));
            bLine.setVisibility(VISIBLE);
            popWinDownUtil.show();
        } else {
            icon = getResources().getDrawable(R.mipmap.iv_drop_gray_small);
            text.setTextColor(getResources().getColor(R.color.colorBack));
            bLine.setVisibility(GONE);
            popWinDownUtil.hide();
        }

        //把箭头画在textview的右边
        text.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    @Override
    public boolean isChecked() {
        return isCheced;
    }

    @Override
    public void toggle() {
        setChecked(!isCheced);
    }

    @Override
    public void onClick(View v) {
        setChecked(!isCheced);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /**
         * 如果条目是已经选中的点击将不收回
         */
        if (selectPostion == position) {
            return;
        }

        drops.get(selectPostion).setChoiced(false);
        drops.get(position).setChoiced(true);
        text.setText(drops.get(position).getText());
        adapter.notifyDataSetChanged();
        selectPostion = position;
        popWinDownUtil.hide();
        if (onDropItemSelectListener != null) {
            onDropItemSelectListener.onDropItemSelect(position);
        }
    }

    @Override
    public void onDismiss() {
        setChecked(false);
    }

    private OnDropItemSelectListener onDropItemSelectListener;

    public void setOnDropItemSelectListener(OnDropItemSelectListener onDropItemSelectListener) {
        this.onDropItemSelectListener = onDropItemSelectListener;
    }

    public interface OnDropItemSelectListener {
        void onDropItemSelect(int postion);
    }

    class DropDownAdapter extends BaseAdapter {
        private List<DropBean> drops;
        private Context context;

        public DropDownAdapter(List<DropBean> drops, Context context) {
            this.drops = drops;
            this.context = context;
        }

        @Override
        public int getCount() {
            return drops.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_dropdown_button, parent, false);
                holder.tv_text_dropdown = convertView.findViewById(R.id.tv_text_dropdown);
                holder.iv_choose = convertView.findViewById(R.id._iv_choose);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_text_dropdown.setText(drops.get(position).getText());
            if (drops.get(position).isChoiced()) {
                holder.iv_choose.setVisibility(VISIBLE);
            } else {
                holder.iv_choose.setVisibility(GONE);
            }
            return convertView;
        }

        private class ViewHolder {
            TextView tv_text_dropdown;
            ImageView iv_choose;
        }
    }
}
