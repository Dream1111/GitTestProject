package com.meng.mengtestapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.meng.mengtestapplication.R;

/**
 * Created by Administrator on 2019/4/15.
 */

public class RoundProgressBar extends View {
    private Paint paint;  //画笔对象的引用
    private int roundColor;  //圆环的颜色
    private int roundProgressColor;  //圆环进度的颜色
    private int textColor;  //中间进度百分比的字符串颜色
    private float textSize;   //中间进度百分比的字符串的字体大小
    private float roundWidth;   //圆环宽度
    private int max;   //最大进度
    private int progress;  //当前进度
    private boolean textIsDispayable;   //是否显示中间的进度
    private int style;    //进度风格，实心或者空心
    public static final int STROKE = 0;
    public static final int FILL = 1;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        //获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.RED);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        textIsDispayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);
        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画最外层的大圆环
         */

        int centre = getWidth() / 2;  //获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2);//圆环的半径
        paint.setColor(roundColor);  //设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE);  //设置空心
        paint.setStrokeWidth(roundWidth);   //设置圆环的宽度
        paint.setAntiAlias(true);   //消除锯齿
        canvas.drawCircle(centre, centre, radius, paint);  //画出圆环

        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);   //设置字体
        //中间的进度百分比，先转换成float在进行除法运算，不然都是0
        int percent = (int) (((float) progress / (float) max) * 100);
        //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        float textWidth = paint.measureText(percent + "%");

        if (textIsDispayable && percent != 0 && style == STROKE) {
            //画进度百分比
            canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize / 2, paint);
        }

        /**
         * 画圆弧，画圆环的进度
         */
        paint.setStrokeWidth(roundWidth);  //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色
        //用于定义的圆弧的形状和大小界限
        RectF oval = new RectF(centre - radius, centre - radius,
                centre + radius, centre + radius);

        switch (style) {
            case STROKE: {
                paint.setStyle(Paint.Style.STROKE);
                //根据进度画圆弧
                canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
                break;
            }
            case FILL: {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0) {
                    canvas.drawArc(oval, 0, 360 * progress / max, true, paint);
                }
                break;
            }
        }
    }


    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度，需要同步
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postIsvalidate() 能在非UI线程刷新
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }
}
