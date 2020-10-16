package com.example.p2p.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

public class FlyLayout extends FrameLayout implements Animation.AnimationListener{

    private static final double MAX_VELOCITY_TOUCH_SLOP = 80;
    private boolean aninationIsPlaying;
    private GestureDetector mGestureDetector;

    public FlyLayout(Context context) {
        super(context);
        init(context);
    }

    public FlyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mGestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (!aninationIsPlaying && Math.hypot(velocityX, velocityY) > MAX_VELOCITY_TOUCH_SLOP) {
                    playAnimation();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }


    private void playAnimation() {
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.setAnimationListener(this);
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            //渐变动画推迟执行
            alphaAnimation.setStartOffset(300);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setDuration(500);
            animationSet.setFillAfter(true);
            View topChildView = getChildAt(getChildCount() - 1);
            topChildView.startAnimation(animationSet);
    }



    //如果单用这个控件只需要改用下面的注释 onTouchEvent
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }
    /* @Override
     public boolean onTouchEvent(MotionEvent event) {

         mGestureDetector.onTouchEvent(event);
         return true;
     }*/
    @Override
    public void onAnimationStart(Animation animation) {
        aninationIsPlaying = true;
    }

    //当动画执行完毕切换到下一个页面
    // topView.clearAnimation();----->触发动画的监听器的调用----->onAnimationEnd
    @Override
    public void onAnimationEnd(Animation animation) {
        View topView = getChildAt(getChildCount() - 1);
        //view的动画执行完毕，下一次再次执行动画需要将之前的动画清除
        //解决动画再次执行可能不会执行
        animation.setAnimationListener(null);
        topView.clearAnimation();
        removeView(topView);

        addView(topView, 0);

        aninationIsPlaying = false;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
