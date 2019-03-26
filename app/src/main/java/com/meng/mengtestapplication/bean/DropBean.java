package com.meng.mengtestapplication.bean;

/**
 * Created by Administrator on 2019/3/26.
 */

public class DropBean {
    private String text;
    private boolean choiced;

    public DropBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChoiced() {
        return choiced;
    }

    public void setChoiced(boolean choiced) {
        this.choiced = choiced;
    }
}
