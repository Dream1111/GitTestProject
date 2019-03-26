package com.meng.mengtestapplication.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2019/3/25.
 */

public class PopWinDownUtil {
    private Context context;
    private View contentView;
    private View relayView;
    private PopupWindow popupWindow;

    public PopWinDownUtil(Context context, View contentView, View relayView) {
        this.context = context;
        this.contentView = contentView;
        this.relayView = relayView;
        init();
    }

    private void init() {
        //内容宽度高度
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        //设置动画
        //popupWindow.setAnimationStyle(R.style.AnimationTopFade);
        //菜单背景颜色
        ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
        popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.setOutsideTouchable(true);

        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (onDismissListener != null) {
                    onDismissListener.onDismiss();
                }
            }
        });
    }

    public void show() {
        //显示位置
        popupWindow.showAsDropDown(relayView);
    }

    public void hide() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    private OnDismissListener onDismissListener;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }
}
