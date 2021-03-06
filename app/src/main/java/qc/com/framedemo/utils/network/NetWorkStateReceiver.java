package qc.com.framedemo.utils.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

/**
 * <ul>
 * <li>功能职责：</li>
 * </ul>
 *
 * @author chengqiu
 * @date 2016-08-08
 */
public class NetWorkStateReceiver extends BroadcastReceiver {
    private static Boolean networkAvailable = false;
    private static NetWorkUtils.netType netType;
    private static ArrayList<NetWorkChangeObserver> netChangeObserverArrayList = new ArrayList<>();
    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public final static String TA_ANDROID_NET_CHANGE_ACTION = "ta.android.net.conn.CONNECTIVITY_CHANGE";
    private static BroadcastReceiver receiver;

    private static BroadcastReceiver getReceiver() {
        if (receiver == null) {
            receiver = new NetWorkStateReceiver();
        }
        return receiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        receiver = NetWorkStateReceiver.this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION) || //
                intent.getAction().equalsIgnoreCase(TA_ANDROID_NET_CHANGE_ACTION)) {
            if (!NetWorkUtils.isNetworkAvailable(context)) {
                networkAvailable = false;
            } else {
                netType = NetWorkUtils.getAPNType(context);
                networkAvailable = true;
            }
            notifyObserver();
        }
    }

    /**
     * 注册网络状态广播
     *
     * @param mContext
     */
    public static void registerNetworkStateReceiver(Context mContext) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(TA_ANDROID_NET_CHANGE_ACTION);
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        mContext.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    /**
     * 检查网络状态
     *
     * @param mContext
     */

    public static void checkNetworkState(Context mContext) {
        Intent intent = new Intent();
        intent.setAction(TA_ANDROID_NET_CHANGE_ACTION);
        mContext.sendBroadcast(intent);
    }

    /**
     * 注销网络状态广播
     *
     * @param mContext
     */
    public static void unRegisterNetworkStateReceiver(Context mContext) {
        if (receiver != null) {
            try {
                mContext.getApplicationContext().unregisterReceiver(receiver);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取当前网络状态，true为网络连接成功，否则网络连接失败
     *
     * @return
     */
    public static Boolean isNetworkAvailable() {
        return networkAvailable;
    }

    public static NetWorkUtils.netType getAPNType() {
        return netType;
    }

    private void notifyObserver() {
        for (int i = 0; i < netChangeObserverArrayList.size(); i++) {
            NetWorkChangeObserver observer = netChangeObserverArrayList.get(i);
            if (observer != null) {
                if (isNetworkAvailable()) {
                    observer.onConnect(netType);
                } else {
                    observer.onDisConnect();
                }
            }
        }
    }

    /**
     * 注册网络连接观察者
     *
     * @param observer
     */
    public static void registerObserver(NetWorkChangeObserver observer) {
        if (netChangeObserverArrayList == null) {
            netChangeObserverArrayList = new ArrayList<NetWorkChangeObserver>();
        }
        netChangeObserverArrayList.add(observer);
    }

    /**
     * 注销网络连接观察者
     *
     * @param observer
     */
    public static void removeRegisterObserver(NetWorkChangeObserver observer) {
        if (netChangeObserverArrayList != null) {
            netChangeObserverArrayList.remove(observer);
        }
    }
}
