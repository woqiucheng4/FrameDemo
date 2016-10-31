package qc.com.framedemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import qc.com.framedemo.utils.ActivitUtils;
import qc.com.framedemo.utils.network.NetWorkChangeObserver;
import qc.com.framedemo.utils.network.NetWorkStateReceiver;
import qc.com.framedemo.utils.network.NetWorkUtils;
import qc.com.framedemo.view.BaseActivity;

/**
 * <ul>
 * <li>功能职责：</li>
 * </ul>
 *
 * @author chengqiu
 * @date 2016-07-29
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    private BaseActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initApplication();
    }

    private void initApplication() {
        NetWorkStateReceiver.registerObserver(taNetChangeObserver);
    }

    public static final BaseApplication getInstance() {
        return instance;
    }

    /**
     * 获取Application Context
     *
     * @return
     */
    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    /**
     * 获取ApplicationInfo
     *
     * @return
     */
    public static ApplicationInfo getAppInfo() {
        return instance.getApplicationInfo();
    }


    NetWorkChangeObserver taNetChangeObserver = new NetWorkChangeObserver() {
        @Override
        public void onConnect(NetWorkUtils.netType type) {
            super.onConnect(type);
            BaseApplication.this.onConnect(type);
        }

        @Override
        public void onDisConnect() {
            super.onDisConnect();
            BaseApplication.this.onDisConnect();

        }
    };


    /**
     * 当前没有网络连接
     */
    protected void onDisConnect() {
        currentActivity = (BaseActivity) ActivitUtils.getInstance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.onDisConnect();
        }
    }

    /**
     * 网络连接连接时调用
     */
    protected void onConnect(NetWorkUtils.netType type) {
        currentActivity = (BaseActivity) ActivitUtils.getInstance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.onConnect(type);
        }
    }

}
