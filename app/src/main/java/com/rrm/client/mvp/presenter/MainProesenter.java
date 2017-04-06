package com.rrm.client.mvp.presenter;

import android.util.Log;

import com.rrm.client.http.ApiCallback;
import com.rrm.client.mvp.model.WeatherModel;
import com.rrm.client.mvp.view.MainView;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class MainProesenter extends BasePresenter<MainView> {
    private static final String TAG = MainProesenter.class.getSimpleName();

    public MainProesenter(MainView view){
        attachView(view);
    }

    public void loadWeather(String cityId){
        mvpView.showLoading();
        addSubscription(apiService.loadWeather(cityId),
                new ApiCallback<WeatherModel>() {
                    @Override
                    public void onSuccess(WeatherModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }
}
