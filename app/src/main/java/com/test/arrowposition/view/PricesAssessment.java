package com.test.arrowposition.view;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.test.arrowposition.R;

import java.util.List;

/**
 * Created by on 2017/5/26.
 */

public class PricesAssessment extends PercentRelativeLayout {

    private TextView down, normal, up, downTitle, normalTitle, upTitle, currentOffer, downOffer, normalnOffer, upOffer;
    private ArrowCanvas downArrow, upArrow, normalArrow;
    private float current = 0;

    public PricesAssessment(Context context) {
        super(context);
        init(context);
    }

    public PricesAssessment(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PricesAssessment(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        final View view = View.inflate(context, R.layout.layout_prices_assessment, this);

        down = (TextView) view.findViewById(R.id.down);
        normal = (TextView) view.findViewById(R.id.normal);
        up = (TextView) view.findViewById(R.id.up);
        downTitle = (TextView) view.findViewById(R.id.downTitle);
        normalTitle = (TextView) view.findViewById(R.id.normalTitle);
        upTitle = (TextView) view.findViewById(R.id.upTitle);
        currentOffer = (TextView) view.findViewById(R.id.currentOffer);
        downArrow = (ArrowCanvas) view.findViewById(R.id.downArrow);
        downArrow.setPaintColor(R.color.check_error);
        downArrow.setLineHigh(150);
        upArrow = (ArrowCanvas) view.findViewById(R.id.upArrow);
        upArrow.setPaintColor(R.color.yellow);
        upArrow.setLineHigh(150);
        normalArrow = (ArrowCanvas) view.findViewById(R.id.normalArrow);
        normalArrow.setPaintColor(R.color.check_ok);
        normalArrow.setLineHigh(150);
        normalArrow.setPosition(true);

        //文字
        downOffer = (TextView) view.findViewById(R.id.downOffer);
        normalnOffer = (TextView) view.findViewById(R.id.normalnOffer);
        upOffer = (TextView) view.findViewById(R.id.upOffer);
    }

    public void setProgress(float down, float normal, float up) {
        PercentRelativeLayout.LayoutParams layoutParams = (LayoutParams) this.down.getLayoutParams();
        layoutParams.getPercentLayoutInfo().widthPercent = down;
        this.down.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.normal.getLayoutParams();
        layoutParams.getPercentLayoutInfo().widthPercent = normal;
        this.normal.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.up.getLayoutParams();
        layoutParams.getPercentLayoutInfo().widthPercent = up;
        this.up.setLayoutParams(layoutParams);

        layoutParams = (LayoutParams) this.downTitle.getLayoutParams();
        layoutParams.getPercentLayoutInfo().widthPercent = down;
        this.downTitle.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.normalTitle.getLayoutParams();
        layoutParams.getPercentLayoutInfo().widthPercent = normal;
        this.normalTitle.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.upTitle.getLayoutParams();
        layoutParams.getPercentLayoutInfo().widthPercent = up;
        this.upTitle.setLayoutParams(layoutParams);

        layoutParams = (LayoutParams) this.downArrow.getLayoutParams();
        int downArrowWidth = (int) (this.downArrow.getArrowWidth() / 3) + 5;
        layoutParams.setMargins(0, 0, -downArrowWidth, 0);
        this.downArrow.setLayoutParams(layoutParams);

        layoutParams = (LayoutParams) this.upArrow.getLayoutParams();
        int upArrowWidth = (int) (this.upArrow.getArrowWidth() / 3) + 5;
        layoutParams.setMargins(0, 0, -upArrowWidth, 0);
        this.upArrow.setLayoutParams(layoutParams);

        setProgressDownText("");
        setProgressNormalText("");
        setProgressUpText("");
    }

    /**
     * 设置布局
     *
     * @param down
     * @param normal
     * @param up
     * @param current 当前报价
     */
    public void setProgress(float down, float normal, float up, float current) {
        setProgress(down, normal, up);
        this.current = current;
        PercentRelativeLayout.LayoutParams layoutParams = (LayoutParams) this.currentOffer.getLayoutParams();
        layoutParams.getPercentLayoutInfo().widthPercent = current;
        this.currentOffer.setLayoutParams(layoutParams);
        caleCurrentOfferColor(down, normal, current);

        layoutParams = (LayoutParams) this.normalArrow.getLayoutParams();
        int normalArrowWidth = (int) (this.normalArrow.getArrowWidth() / 3) + 5;
        layoutParams.setMargins(0, 0, -normalArrowWidth, 0);
        this.normalArrow.setLayoutParams(layoutParams);
    }

    public void setProgressDownText(String downText) {
        if (isEmpty(downText))
            downText = this.downOffer.getText().toString();

        PercentRelativeLayout.LayoutParams layoutParams = (LayoutParams) this.downOffer.getLayoutParams();
        float widthDown = getTextWidth(this.downOffer, downText) / 3;
        layoutParams.setMargins(0, 0, -(int) widthDown, 0);
        this.downOffer.setLayoutParams(layoutParams);
        this.downOffer.setText(downText);
    }

    public void setProgressNormalText(String normalText) {
        if (isEmpty(normalText))
            normalText = this.normalnOffer.getText().toString();
        PercentRelativeLayout.LayoutParams layoutParams = (LayoutParams) this.normalnOffer.getLayoutParams();
        float widthNormal = getTextWidth(this.normalnOffer, normalText);
        if (current <= 0.15) {
            layoutParams.setMargins(0, 0, -(int) widthNormal, 0);
        } else if (current >= 0.85) {
            layoutParams.setMargins(0, 0, 0, 0);
        } else {
            widthNormal=widthNormal/2;
            layoutParams.setMargins(0, 0, -(int) widthNormal, 0);
        }
        this.normalnOffer.setLayoutParams(layoutParams);
        this.normalnOffer.setText(normalText);
    }

    public void setProgressUpText(String upText) {
        if (isEmpty(upText))
            upText = this.upOffer.getText().toString();
        PercentRelativeLayout.LayoutParams layoutParams = (LayoutParams) this.upOffer.getLayoutParams();
        float widthUp = getTextWidth(this.upOffer, upText) / 3;
        layoutParams.setMargins(0, 0, -(int) widthUp, 0);
        this.upOffer.setLayoutParams(layoutParams);
        this.upOffer.setText(upText);
    }

    /* 计算当前价格的颜色 */
    public void caleCurrentOfferColor(float down, float normal, float current) {
        if (current <= down) {
            this.normalArrow.setPaintColor(R.color.check_error);
            this.normalnOffer.setBackgroundResource(R.drawable.button_circular3_check_error);
        } else if (current > down && current <= (down + normal)) {
            this.normalArrow.setPaintColor(R.color.check_ok);
            this.normalnOffer.setBackgroundResource(R.drawable.button_circular3_check_ok);
        } else {
            this.normalArrow.setPaintColor(R.color.yellow);
            this.normalnOffer.setBackgroundResource(R.drawable.button_circular3_yellow);
        }
    }

    /* 获取TextView的宽度*/
    public float getTextWidth(TextView textView, String text) {
        TextPaint textPaint = textView.getPaint();
        float textPaintWidth = textPaint.measureText(text);
        return textPaintWidth;
    }

    /* 判断字符是否为空 */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            if ("".equals(((String) object).trim()) || "null".equals((String) object)) {
                return true;
            } else {
                return false;
            }
        }

        if (object instanceof List) {
            return ((List) object).isEmpty();
        }

        return false;

    }
}
