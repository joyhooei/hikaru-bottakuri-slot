package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.b.g;
import android.util.Base64;
import android.util.Log;
import twitter4j.HttpResponseCode;

public final class FirebaseInstanceIdReceiver extends g {
    public int a(Context context, String str, Intent intent) {
        return g.a().a(context, str, intent);
    }

    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            setResultCode(HttpResponseCode.INTERNAL_SERVER_ERROR);
        }
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        stringExtra = intent.getStringExtra("from");
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction()) || "google.com/iid".equals(stringExtra) || "gcm.googleapis.com/refresh".equals(stringExtra)) {
            stringExtra = "com.google.firebase.INSTANCE_ID_EVENT";
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            stringExtra = "com.google.firebase.MESSAGING_EVENT";
        } else {
            Log.d("FirebaseInstanceId", "Unexpected intent");
            stringExtra = null;
        }
        int i = -1;
        if (stringExtra != null) {
            i = a(context, stringExtra, intent);
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }
}