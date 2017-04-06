package com.rrm.client.mvp.view;

import com.rrm.client.mvp.model.WeatherModel;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public interface MainView extends BaseView{
    /**
     * 获取数据成功
     * @param model
     */
    void getDataSuccess(WeatherModel model);

    /**
     * 获取数据失败
     * @param msg
     */
    void getDataFail(String msg);

}
