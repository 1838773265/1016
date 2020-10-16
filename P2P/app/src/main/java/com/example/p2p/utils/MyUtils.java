package com.example.p2p.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

public class MyUtils {

    public static int dipToPX(final Context ctx, float dip){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
    }

    //返回指定colorId对应的颜色值
    public static int getColor(final Context ctx,int colorId){
        return ctx.getResources().getColor(colorId);
    }

    //加载指定viewId的视图对象，并返回
    public static View getView(final Context ctx,int viewId){
        View view = View.inflate(ctx, viewId, null);
        return view;
    }

    public static String[] getStringArr(final Context ctx,int strArrId){
        String[] stringArray = ctx.getResources().getStringArray(strArrId);
        return stringArray;
    }

    //将dp转化为px
    public static int dp2px(final Context ctx,int dp){
        //获取手机密度
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);//实现四舍五入
    }

    public static int px2dp(final Context ctx,int px){
        //获取手机密度
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//实现四舍五入
    }

//    //保证runnable中的操作在主线程中执行
//    public static void runOnUiThread(Runnable runnable) {
//        if(isInMainThread()){
//            runnable.run();
//        }else{
//            UIUtils.getHandler().post(runnable);
//        }
//    }
//    //判断当前线程是否是主线程
//    private static boolean isInMainThread() {
//        int currentThreadId = android.os.Process.myTid();
//        return MyApplication.mainThreadId == currentThreadId;
//
//    }

    public static void toast(Context context, String message,boolean isLengthLong){
        Toast.makeText(context, message,isLengthLong? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

}
