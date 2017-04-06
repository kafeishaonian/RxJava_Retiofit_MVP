package com.rrm.client.http;

import android.util.Log;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 请求返回数据
 * Created by Administrator on 2017/4/6 0006.
 */
public abstract class ApiCallback<M> extends Subscriber<M> {
    /**
     * TAG
     */
    private static final String TAG = ApiCallback.class.getSimpleName();
    /**
     * 返回成功
     * @param model
     */
    public abstract void onSuccess(M model);

    /**
     * 返回失败
     * @param msg
     */
    public abstract void onFailure(String msg);

    /**
     * 请求结束
     */
    public abstract void onFinish();


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException){
            HttpException httpException  = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            Log.e(TAG, "code = " + code);
            if (code == 504){
                msg = "网络不给力";
            }
            if (code == 502 || code == 404){
                msg = "服务器异常，稍后再试";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
