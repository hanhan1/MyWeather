package com.hyh.fistproject;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatDelegate;

/**
 * 作者：hyh on 2016/7/22 17:11
 * github: https://github.com/hanhan1
 * QQ:549551740
 * 邮箱：hyh5878@163.com
 * 作用：
 */
public class BaseApplication extends Application {
    public static String cacheDir;
    public static Context mAppContext = null;
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext =getApplicationContext();
        /**
         * 如果sdcard存在则写入sdcard，否则写入手机内存
         */
        if(getApplicationContext().getExternalCacheDir()!= null && ExistSDCard()){
           cacheDir = getApplicationContext().getExternalCacheDir().toString();
        }else {
            cacheDir =getApplicationContext().getCacheDir().toString();
        }

    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED);
    }

    public static Context getmAppContext(){return mAppContext;}
}
