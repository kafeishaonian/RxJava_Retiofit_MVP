package com.rrm.client.mvp.module;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.rrm.client.mvp.presenter.BasePresenter;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public abstract class MVPActivity<P extends BasePresenter> extends Activity {

    private static final String TAG = MVPActivity.class.getSimpleName();

    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected  abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null){
            mvpPresenter.detachView();
        }
    }

    protected abstract void init();

    public void showLoading() {
        //TODO 调用页面刷新
        Log.e(TAG, "页面刷新");
    }

    public void hideLoading() {
        //TODO 取消页面刷新
        Log.e(TAG, "页面取消刷新");
    }

}
