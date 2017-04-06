package com.rrm.client.mvp.presenter;

import com.rrm.client.http.APIClient;
import com.rrm.client.http.ApiService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class BasePresenter<V> {

    public V mvpView;
    protected ApiService apiService;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView){
        this.mvpView = mvpView;
        apiService = APIClient.retrofit().create(ApiService.class);
    }

    public void detachView(){
        this.mvpView = null;
        onUnsubscribe();
    }

    /**
     * Rxjava取消注册，以避免内存泄漏
     */
    public void onUnsubscribe(){
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber){
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }

        mCompositeSubscription.add(observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber));
    }

}
