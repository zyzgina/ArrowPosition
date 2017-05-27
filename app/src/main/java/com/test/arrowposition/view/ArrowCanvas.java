package com.test.arrowposition.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.test.arrowposition.R;


/**
 * 自定义箭头
 * Created by gina on 2017/5/25.
 */

public class ArrowCanvas extends View {

    private Canvas myCanvas;
    private Paint myPaint = new Paint();
    private int strokeWidth = 5;//画笔大小
    private boolean flag = false;// false 向上 true 向下
    private int lineHigh = 100;//设置线的高度
    private int color = R.color.back;//画笔的颜色
    private int startHigh = 20;//箭头的起始高度

    public ArrowCanvas(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public ArrowCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public ArrowCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        this.myCanvas = canvas;
        myPaint.setStrokeWidth(strokeWidth);
        myPaint.setColor(getResources().getColor(color));
        if (flag)
            drawDown();
        else
            drawUp();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int eX = 50 + strokeWidth * 2;
        int sX = 30 - strokeWidth * 2;
        int sY = startHigh + strokeWidth * 2;
        int width = eX - sX + 15;
        int height = lineHigh + sY+5;
        setMeasuredDimension(width, height);
    }

    /**
     * 画箭头朝下箭头
     */
    public void drawDown() {
        int sY = startHigh + strokeWidth * 2;
        int eX = 40 + strokeWidth * 2;
        int sX = 20 - strokeWidth * 2;
        // 画线
        myCanvas.drawLine(30, 0, 30, lineHigh, myPaint);// 画线
        Path triangle = new Path();
        triangle.moveTo(sX, lineHigh);// 此点为多边形的起点
        triangle.lineTo(eX, lineHigh);
        int eY = lineHigh + sY;
        triangle.lineTo(30, eY);
        triangle.close();
        myCanvas.drawPath(triangle, myPaint);
    }

    /**
     * 画箭头朝上
     */
    public void drawUp() {
        int sY = startHigh + strokeWidth * 2+5;
        int eX = 40 + strokeWidth * 2;
        int sX = 20 - strokeWidth * 2;
        Path triangle = new Path();
        triangle.moveTo(sX, sY);// 此点为多边形的起点
        triangle.lineTo(eX, sY);
        triangle.lineTo(30, 5);//顶点
        triangle.close();
        myCanvas.drawPath(triangle, myPaint);
        myCanvas.drawLine(30, sY, 30, lineHigh + sY, myPaint);// 画线
    }

    /* 设置画笔的大小 */
    public void setPaintSize(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /* 判断箭头的方向*/
    public void setPosition(boolean flag) {
        this.flag = flag;
    }

    /* 设置线的高度 */
    public void setLineHigh(int lineHigh) {
        this.lineHigh = lineHigh;
    }

    /* 设置画笔的颜色 */
    public void setPaintColor(int color) {
        this.color = color;
    }

    /* 设置箭头的其实高度 */
    public void setStartHigh(int startHigh) {
        this.startHigh = startHigh;
    }

    /* 返回箭头的宽度 */
    public float getArrowWidth(){
        int eX = 50 + strokeWidth * 2;
        int sX = 30 - strokeWidth * 2;
        int width = eX - sX + 15;
        return width;
    }

}