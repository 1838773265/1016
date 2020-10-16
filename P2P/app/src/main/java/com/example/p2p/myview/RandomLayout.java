package com.example.p2p.myview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomLayout extends FrameLayout {

    private static final int MAX_TRY_LAYOUT_COUNT = 30;

    public RandomLayout(Context context) {
        super(context);
    }

    public RandomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RandomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private List<ChildViewIndexArea> mChildViewIndexAreas = new ArrayList<>();

    private static class ChildViewIndexArea implements Comparable<ChildViewIndexArea> {

        public int childIndex;
        public View childView;

        public int area;

        public ChildViewIndexArea(int childIndex, View childView) {
            this.childIndex = childIndex;
            this.childView = childView;
            area = childView.getMeasuredWidth() * childView.getMeasuredHeight();
        }
        //安卓控件的面积大小：从大到小排序
        @Override
        public int compareTo(ChildViewIndexArea another) {

            return -(this.area - another.area);
        }
    }




    private Random mRandom = new Random();
    // 按照面积从小到大顺序随机摆放，并且每个子控件尝试 30次
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mLayoutedChildViews.clear();
        mChildViewIndexAreas.clear();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //将每一个子控件封装成类放入集合中
            ChildViewIndexArea childViewIndexArea = new ChildViewIndexArea(i, getChildAt(i));
            mChildViewIndexAreas.add(childViewIndexArea);
        }

        //排序
        Collections.sort(mChildViewIndexAreas);

        for (ChildViewIndexArea childViewIndexArea : mChildViewIndexAreas) {

            View childView = childViewIndexArea.childView;
            int childViewMeasuredWidth = childView.getMeasuredWidth();
            int childViewMeasuredHeight = childView.getMeasuredHeight();

            //随机摆放的高度和宽度

            //多次去尝试摆放
            int tryLayoutCount = MAX_TRY_LAYOUT_COUNT;
            while (tryLayoutCount-- > 0) {
                //摆放成功跳出循环
                if (tryLayoutChildView(measuredWidth, measuredHeight, childView, childViewMeasuredWidth, childViewMeasuredHeight)) {

                    break;
                }
            }
            //摆放失败
            if (tryLayoutCount <= 0) {

                //重新摆放的时候，如果控件摆放失败，会回到原来的位置
                Log.d("RandomLayout", ((TextView) childView).getText().toString() + " 摆放失败");
                childView.layout(-1, -1, -1, -1);
            }
        }
    }

    //已经摆放好的子控件
    private List<View> mLayoutedChildViews = new ArrayList<>();

    //这个方法应该多次调用，去尝试摆放的位置
    private boolean tryLayoutChildView(int measuredWidth, int measuredHeight, View childView, int childViewMeasuredWidth, int childViewMeasuredHeight) {

        int childLeft = mRandom.nextInt(measuredWidth - childViewMeasuredWidth);
        int childTop = mRandom.nextInt(measuredHeight - childViewMeasuredHeight);
        int childRight = childLeft + childViewMeasuredWidth;
        int childBottom = childTop + childViewMeasuredHeight;


        //直接摆放了，摆放之前应该判断该位置是否可以摆放
        if (canLayout(childLeft, childTop, childRight, childBottom)) {

            childView.layout(childLeft, childTop, childRight, childBottom);
            mLayoutedChildViews.add(childView);
            return true;
        }
        return false;
    }

    //每个控件摆放的时候去遍历所有已经摆放的子控件，判断(int childLeft, int childTop, int childRight, int childBottom)
    //和每个已经摆放的子控件是否有交点new Rect().intersect()判断两个矩形是否有重合部分
    //view---->getHitRect 返回view的left, top, right, botoom

    private Rect mLayoutedViewRect = new Rect();

    private boolean canLayout(int childLeft, int childTop, int childRight, int childBottom) {

        for (View layoutedChildView : mLayoutedChildViews) {

            //将摆放的子控件的位置封装到mLayoutedViewRect
            layoutedChildView.getHitRect(mLayoutedViewRect);
            if (mLayoutedViewRect.intersect(childLeft, childTop, childRight, childBottom)) {

                return false;
            }
        }
        return true;
    }


}
