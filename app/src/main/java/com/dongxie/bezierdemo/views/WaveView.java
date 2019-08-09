package com.dongxie.bezierdemo.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.dongxie.bezierdemo.R;

/**
 * @ClassName: SecondBezierView
 * @Description:
 * @Author: dongxie
 * @CreateDate: 2019/8/8 15:43
 */
public class WaveView extends View implements View.OnClickListener {


    private Path mPath;
    //画线的画笔
    private Paint mPaintBezier;
    private int mWaveLength;
    private int mScreenWith;
    private int mScreenHeight;
    private int mCenterY;
    private int mWaveCount;
    private ValueAnimator mValueAnimator;
    private int offSet;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setColor(getResources().getColor(R.color.title));
        mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);
        mWaveLength = 800;


    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenHeight = h;
        mScreenWith = w;
        mCenterY = h / 2;
        mWaveCount = (int) Math.round(mScreenWith / mWaveLength + 1.5);
        mPath = new Path();

        setOnClickListener(this);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();

        mPath.moveTo(-mWaveLength + offSet, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + offSet, mCenterY + 60, -mWaveLength / 2 + i * mWaveLength + offSet, mCenterY);
            mPath.quadTo(-mWaveLength * 1 / 4 + i * mWaveLength + offSet, mCenterY - 60, i * mWaveLength + offSet, mCenterY);
        }
        mPath.lineTo(mScreenWith, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaintBezier);

    }

    @Override
    public void onClick(View v) {
        mValueAnimator = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offSet = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }
}
