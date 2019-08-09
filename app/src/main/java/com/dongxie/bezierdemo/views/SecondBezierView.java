package com.dongxie.bezierdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dongxie.bezierdemo.R;

/**
 * @ClassName: SecondBezierView
 * @Description: 绘制二阶贝塞尔曲线
 * @Author: dongxie
 * @CreateDate: 2019/8/8 15:43
 */
public class SecondBezierView extends View {

    /**
     * 起始点
     */
    private float mStartPointX;
    private float mStartPointY;
    /**
     * 终点
     */
    private float mEndPointX;
    private float mEndPointY;

    /**
     * 控制点
     */
    private float mFlagPointX;
    private float mFlagPointY;

    private Path mPath;
    //画线的画笔
    private Paint mPaintBezier;
    //画点的画笔
    private Paint mPaintFlag;
    //画文字的画笔
    private Paint mPaintFlagText;

    public SecondBezierView(Context context) {
        super(context);
    }

    public SecondBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setColor(getResources().getColor(R.color.red));
        mPaintBezier.setStyle(Paint.Style.STROKE);

        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlag.setColor(getResources().getColor(R.color.yellow));
        mPaintFlag.setStrokeWidth(5);
        mPaintFlag.setStyle(Paint.Style.STROKE);


        mPaintFlagText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlagText.setStyle(Paint.Style.STROKE);
        mPaintFlagText.setColor(getResources().getColor(R.color.green));
        mPaintFlagText.setTextSize(25);

    }

    public SecondBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 8;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 7 / 8;
        mEndPointY = h / 2 - 200;

        mFlagPointX = w / 2;
        mFlagPointY = h / 4 - 300;

        mPath = new Path();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();

        mPath.moveTo(mStartPointX, mStartPointY);

        /**
         * mPath.rQuadTo()与mPath.quadTo()都可用来绘制Bezier曲线
         * 区别在于前一个使用的是相对坐标，后一个使用的是绝对坐标
         */
        mPath.quadTo(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY);
        //画点
        canvas.drawPoint(mStartPointX, mStartPointY, mPaintFlag);
        //画文字
        canvas.drawText("起点", mStartPointX, mStartPointY, mPaintFlagText);
        canvas.drawPoint(mFlagPointX, mFlagPointY, mPaintFlag);
        canvas.drawText("控制点", mFlagPointX, mFlagPointY, mPaintFlagText);
        canvas.drawPoint(mEndPointX, mEndPointY, mPaintFlag);
        canvas.drawText("终点", mEndPointX, mEndPointY, mPaintFlagText);
        //画辅助线
        canvas.drawLine(mStartPointX, mStartPointY, mFlagPointX, mFlagPointY, mPaintFlag);
        canvas.drawLine(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY, mPaintFlag);
        //画路径
        canvas.drawPath(mPath, mPaintBezier);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mFlagPointX = event.getX();
                mFlagPointY = event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
