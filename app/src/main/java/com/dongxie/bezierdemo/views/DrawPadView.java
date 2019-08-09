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
 * @ClassName: DrawPadView
 * @Description: 处理两点间连线的圆滑处理
 * @Author: dongxie
 * @CreateDate: 2019/8/9 9:20
 */
public class DrawPadView extends View {

    private Path mPath;
    private Paint mPaint;
    private float mX;
    private float mY;


    public DrawPadView(Context context) {
        super(context);
    }

    public DrawPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(getResources().getColor(R.color.yellow));

        mPath = new Path();
    }

    public DrawPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX, mY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x1, y1;
                x1 = event.getX();
                y1 = event.getY();
                float cx = (x1 + mX) / 2;
                float cy = (y1 + mY) / 2;

                //使用二阶贝塞尔曲线代替lineTo方法，解决连线的生硬，使得曲线更加圆滑
                mPath.quadTo(mX, mY, cx, cy);
                //产生棱角的原因
                //mPath.lineTo(x1, y1);
                mX = x1;
                mY = y1;
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mPaint);
    }
}
