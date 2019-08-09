package com.dongxie.bezierdemo;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import com.dongxie.bezierdemo.utils.BezierUtils;

/**
 * @ClassName: BezierEvaluator
 * @Description:
 * @Author: dongxie
 * @CreateDate: 2019/8/9 14:27
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF mFlagPoint;

    public BezierEvaluator(PointF flagPoint) {
        this.mFlagPoint = flagPoint;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return BezierUtils.CalculateBezierPointForQuadratic(fraction, startValue, mFlagPoint, endValue);
    }
}
