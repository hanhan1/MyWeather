package com.hyh.fistproject;

import android.app.Fragment;
import android.os.Bundle;

/**
 * 作者：hyh on 2016/7/22 17:04
 * github: https://github.com/hanhan1
 * QQ:549551740
 * 邮箱：hyh5878@163.com
 * 作用：
 */
public abstract class BaseFragment extends Fragment{
    protected boolean isCreateView = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreateView){
            lazyLoad();
        }
    }

    /**
     * 加载数据操作，在视图创建之前初始化
     */
    protected abstract void lazyLoad();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //第一个fragment会调用
        if(getUserVisibleHint()){
            lazyLoad();
        }
    }
}
