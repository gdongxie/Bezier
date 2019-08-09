package com.dongxie.bezierdemo.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dongxie.bezierdemo.BezierEvaluator;

/**
 * @ClassName: PathBezierView
 * @Description:
 * @Author: dongxie
 * @CreateDate: 2019/8/9 14:09
 */
public class PathBezierView extends View implements View.OnClickListener {

    private int mStartPointX;
    private int mStartPointY;

    private int mEndPointX;
    private int mEndPointY;

    private int mFlagPointX;
    private int mFlagPointY;

    private Path mPath;
    private Paint mPaint;
    private Paint mPaintCircle;

    private int mMovePointX;
    private int mMovePointY;

    public PathBezierView(Context context) {
        super(context);
    }

    public PathBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.GREEN);

        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(8);
        mPaintCircle.setColor(Color.RED);

        mStartPointX = 100;
        mStartPointY = 100;

        mEndPointX = 600;
        mEndPointY = 600;

        mFlagPointX = 500;
        mFlagPointY = 0;

        mMovePointX = mStartPointX;
        mMovePointY = mStartPointY;

        setOnClickListener(this);
    }

    public PathBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStartPointX, mStartPointY, 20, mPaintCircle);
        canvas.drawCircle(mEndPointX, mEndPointY, 20, mPaintCircle);
        canvas.drawCircle(mMovePointX, mMovePointY, 20, mPaintCircle);
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);

        mPath.quadTo(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY);

        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void onClick(View v) {
        BezierEvaluator bezierEvaluator = new BezierEvaluator(new PointF(mFlagPointX, mFlagPointY));
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierEvaluator, new PointF(mStartPointX, mStartPointY), new PointF(mEndPointX, mEndPointY));
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                mMovePointX = (int) pointF.x;
                mMovePointY = (int) pointF.y;
                invalidate();
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }
}
