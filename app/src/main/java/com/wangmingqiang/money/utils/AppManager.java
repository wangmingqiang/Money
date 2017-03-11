package com.wangmingqiang.money.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wangmingqiang on 2017/3/11.
 */

public class AppManager {
    /**
     * 统一应用程序中所有的Activity的栈管理（单例）
     * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
     */
    private AppManager(){};

    private static AppManager appManager=new AppManager();

    public static AppManager getInstance(){
        return appManager;
    }

    private Stack<Activity> stack=new Stack<>();


    private void addActivity(Activity activity){
        if(activity!=null) {
            stack.add(activity);
        }
    }
}
