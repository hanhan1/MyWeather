package com.hyh.fistproject;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：hyh on 2016/7/25 14:47
 * github: https://github.com/hanhan1
 * QQ:549551740
 * 邮箱：hyh5878@163.com
 * 作用：设置相关 包括 sp的写入
 */
public class SharedPreferenceUtil {

    public static final String CHANGE_ICONS = "change_icons"; //切换图标
    public static final String CLEAR_CACHE = "clear_cache";//清空缓存
    public static final String AUTO_UPDATE = "change_update_time";//自动更新时间
    public static final String CITY_NAME = "城市";
    public static final String HOUR = "current_hour";//当前小时
    public static final String NOTIFICATION_MODEL = "notification_model";

    public static int ONE_HOUR = 1000*60*60;
    private static SharedPreferenceUtil sInstance;
    private SharedPreferences mPrefs;

    public SharedPreferenceUtil(Context mAppContext) {
        mPrefs =mAppContext.getSharedPreferences("setting",Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtil getsInstance(){
        if(sInstance == null ){
            sInstance = new SharedPreferenceUtil(BaseApplication.mAppContext);
        }
        return sInstance;
    }

    public SharedPreferenceUtil putInt(String key,int value){
        mPrefs.edit().putInt(key,value).apply();
        return this;
    }

    public int getInt(String key,int defvalue){
        return mPrefs.getInt(key,defvalue);
    }

    public SharedPreferenceUtil putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }


    // 设置当前小时
    public void setCurrentHour(int h){
        mPrefs.edit().putInt(HOUR,h).apply();
    }
    public int getCurrentHour(){
        return mPrefs.getInt(HOUR,0);
    }

    // 图标种类相关
    public void setIconType(int type) {
        mPrefs.edit().putInt(CHANGE_ICONS, type).apply();
    }

    public int getIconType() {
        return mPrefs.getInt(CHANGE_ICONS, 0);
    }

    // 自动更新时间 hours
    public void setAutoUpdate(int t) {
        mPrefs.edit().putInt(AUTO_UPDATE, t).apply();
    }

    public int getAutoUpdate() {
        return mPrefs.getInt(AUTO_UPDATE, 3);
    }

    //当前城市
    public void setCityName(String name) {
        mPrefs.edit().putString(CITY_NAME, name).apply();
    }

    public String getCityName() {return mPrefs.getString(CITY_NAME, "北京");}

    //  通知栏模式 默认为常驻
    public void setNotificationModel(int t) {
        mPrefs.edit().putInt(NOTIFICATION_MODEL, t).apply();
    }
    public int getNotificationModel() {
        return mPrefs.getInt(NOTIFICATION_MODEL, Notification.FLAG_AUTO_CANCEL);
    }
}
