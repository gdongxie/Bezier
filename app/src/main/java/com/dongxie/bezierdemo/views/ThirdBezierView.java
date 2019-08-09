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
 * @Description:  绘制三阶贝塞尔曲线
 * @Author: dongxie
 * @CreateDate: 2019/8/8 15:43
 */
public class ThirdBezierView extends View {

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
     * 控制点1
     */
    private float mFlagPointOneX;
    private float mFlagPointOneY;
    /**
     * 控制点2
     */
    private float mFlagPointTwoX;
    private float mFlagPointTwoY;

    private Path mPath;
    //画线的画笔
    private Paint mPaintBezier;
    //画点的画笔
    private Paint mPaintFlag;
    //画文字的画笔
    private Paint mPaintFlagText;

    private boolean isSecondPoint = false;

    public ThirdBezierView(Context context) {
        super(context);
    }

    public ThirdBezierView(Context context, AttributeSet attrs) {
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

    public ThirdBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 8;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 7 / 8;
        mEndPointY = h / 2 - 200;

        mFlagPointOneX = w / 2 - 100;
        mFlagPointOneY = h / 4 - 300;

        mFlagPointTwoX = w / 2 + 100;
        mFlagPointTwoY = h / 4 - 300;
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
         *  mPath.cubicTo 画三阶贝赛尔曲线
         */
        mPath.cubicTo(mFlagPointOneX, mFlagPointOneY, mFlagPointTwoX, mFlagPointTwoY, mEndPointX, mEndPointY);
        //画点
        canvas.drawPoint(mStartPointX, mStartPointY, mPaintFlag);
        //画文字
        canvas.drawText("起点", mStartPointX, mStartPointY, mPaintFlagText);
        canvas.drawPoint(mFlagPointOneX, mFlagPointOneY, mPaintFlag);
        canvas.drawText("控制点", mFlagPointOneX, mFlagPointOneY, mPaintFlagText);
        canvas.drawPoint(mFlagPointTwoX, mFlagPointTwoY, mPaintFlag);
        canvas.drawText("控制点", mFlagPointTwoX, mFlagPointTwoY, mPaintFlagText);
        canvas.drawPoint(mEndPointX, mEndPointY, mPaintFlag);
        canvas.drawText("终点", mEndPointX, mEndPointY, mPaintFlagText);
        //画辅助线
        canvas.drawLine(mStartPointX, mStartPointY, mFlagPointOneX, mFlagPointOneY, mPaintFlag);
        canvas.drawLine(mFlagPointOneX, mFlagPointOneY, mFlagPointTwoX, mFlagPointTwoY, mPaintFlag);
        canvas.drawLine(mFlagPointTwoX, mFlagPointTwoY, mEndPointX, mEndPointY, mPaintFlag);
        //画路径
        canvas.drawPath(mPath, mPaintBezier);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            //多点触控
            case MotionEvent.ACTION_POINTER_DOWN:
                isSecondPoint = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isSecondPoint = false;
                break;
            case MotionEvent.ACTION_MOVE:
                mFlagPointOneX = event.getX(0);
                mFlagPointOneY = event.getY(0);
                if (isSecondPoint) {
                    mFlagPointTwoX = event.getX(1);
                    mFlagPointTwoY = event.getY(1);
                }
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
