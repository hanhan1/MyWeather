package com.hyh.fistproject;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hyh on 2016/7/22.
 */
public class BaseActivity extends AppCompatActivity{
    public ACache aCache;
    public SharedPreferenceUtil mSharedPreferenceUtil = null;
    private CompositeSubscription mComposoteSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (savedInstanceState == null){
            setTheme(this);
        }
        aCache = ACache.get(getApplication());
        mSharedPreferenceUtil = SharedPreferenceUtil.getsInstance();

        //setStatusBarColorForKitkat();
    }

    public CompositeSubscription getmComposoteSubscription(){
        if(this.mComposoteSubscription == null){
            this.mComposoteSubscription = new CompositeSubscription();
        }
        return this.mComposoteSubscription;
    }

    public void addSubscription(Subscription s){
        if(this.mComposoteSubscription == null){
            this.mComposoteSubscription =new CompositeSubscription();
        }
        this.mComposoteSubscription.add(s);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.mComposoteSubscription != null){
            this.mComposoteSubscription.unsubscribe();
        }
    }

    public static void setDayTheme(AppCompatActivity activity){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        activity.recreate();
    }

    public static void setNightTheme(AppCompatActivity activity){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        activity.recreate();
    }

    private void setStatusBarColorForKitkat(int color) {
        /**
         * Android 4.4
         *
         */
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT){
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);
        }

    }

    public void setTheme(AppCompatActivity activity){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        activity.recreate();
    }
}
