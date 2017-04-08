package com.example.ncrsoft.food.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by NcrSoft on 01-Mar-17.
 */

public class ConnectionManager {
    public static boolean isNetworkOnline(Context context) {
        Boolean bNetwork = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        for (NetworkInfo networkInfo : connectivityManager.getAllNetworkInfo()) {
            int netType = networkInfo.getType();
            int netSubType = networkInfo.getSubtype();

            if (netType == ConnectivityManager.TYPE_WIFI) {
                bNetwork = networkInfo.isConnected();
                if (bNetwork == true)
                    break;
            } else if (netType == ConnectivityManager.TYPE_MOBILE && netSubType != TelephonyManager.NETWORK_TYPE_UNKNOWN) {
                bNetwork = networkInfo.isConnected();
                if (bNetwork == true)
                    break;
            } else {
                bNetwork = false;
            }
        }
        if (!bNetwork) {
        }

        return bNetwork;
    }
}
