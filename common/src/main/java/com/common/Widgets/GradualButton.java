package com.common.Widgets;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;


/**
 * Created by vange on 2017/10/12.
 */

public class GradualButton extends android.support.v7.widget.AppCompatButton {


    private ObjectAnimator objectAnimator;

    public GradualButton(Context context) {
        this(context, null);
    }

    public GradualButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradualButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        setClickable(true);
        setSoundEffectsEnabled(true);
    }


    public void start(int startColor, int endColor, long during) {
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofInt(this, "textColor", startColor, endColor);
            objectAnimator.setEvaluator(new HsvEvaluator());
            objectAnimator.setDuration(during);
            objectAnimator.setRepeatCount(-1);
            objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        }else{
            objectAnimator.cancel();
        }
        objectAnimator.start();

    }
    public void start(int startColor, int endColor) {
        start(startColor,endColor,1000);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator = null;
        }
    }
}
