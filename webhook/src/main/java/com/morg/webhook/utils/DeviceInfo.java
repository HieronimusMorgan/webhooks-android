package com.morg.webhook.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;


public class DeviceInfo {

    @SuppressLint("MissingPermission")
    public static String getUniqueID(Context context) {
        String uniqueID;
        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            uniqueID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            uniqueID = tManager.getDeviceId();
        }
        return uniqueID;
    }

}
