package com.rrm.client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rrm.client.mvp.model.WeatherModel;
import com.rrm.client.mvp.module.MVPActivity;
import com.rrm.client.mvp.presenter.MainProesenter;
import com.rrm.client.mvp.view.MainView;

public class MainActivity extends MVPActivity<MainProesenter> implements MainView {

    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }




    @Override
    protected MainProesenter createPresenter() {
        return new MainProesenter(this);
    }

    @Override
    protected void init() {
        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.loadWeather("101310222");
            }
        });
    }

    @Override
    public void getDataSuccess(WeatherModel model) {
        //接口回调成功
        dataSuccess(model);
    }

    @Override
    public void getDataFail(String msg) {
        Toast.makeText(MainActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
    }


    private void dataSuccess(WeatherModel model){
        WeatherModel.WeatherinfoBean info = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + info.getCity()
                + getResources().getString(R.string.wd) + info.getWD()
                + getResources().getString(R.string.ws) + info.getWS()
                + getResources().getString(R.string.time) + info.getTime();
        text.setText(showData);
    }
}
