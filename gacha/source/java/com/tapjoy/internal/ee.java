package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build.VERSION;
import com.tapjoy.FiveRocksIntegration;
import com.tapjoy.TJAdUnit;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TJAwardCurrencyListener;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJCurrency;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJEventOptimizer;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJSetUserIDListener;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJVideoListener;
import com.tapjoy.TapjoyAppSettings;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.TapjoyException;
import com.tapjoy.TapjoyIntegrationException;
import com.tapjoy.TapjoyLog;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.cocos2dx.lib.BuildConfig;

class ee extends ed {
    private boolean b = false;
    private String c = BuildConfig.FLAVOR;
    private TJCurrency d = null;
    private TapjoyCache e = null;

    ee() {
    }

    public final String b() {
        return com.tapjoy.mraid.BuildConfig.VERSION_NAME;
    }

    public final void a(boolean z) {
        TapjoyLog.setDebugEnabled(z);
    }

    public final boolean a(Context context, String str) {
        return a(context, str, null, null);
    }

    public synchronized boolean a(final Context context, String str, Hashtable hashtable, final TJConnectListener tJConnectListener) {
        boolean z = false;
        synchronized (this) {
            TapjoyConnectCore.setSDKType(TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
            if (context == null) {
                TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "The application context is NULL"));
                if (tJConnectListener != null) {
                    tJConnectListener.onConnectFailure();
                }
            } else {
                FiveRocksIntegration.a(hashtable);
                try {
                    TapjoyAppSettings.init(context);
                    TapjoyConnectCore.requestTapjoyConnect(context, str, hashtable, new TJConnectListener(this) {
                        final /* synthetic */ ee c;

                        public final void onConnectSuccess() {
                            this.c.d = new TJCurrency(context);
                            this.c.e = new TapjoyCache(context);
                            try {
                                TJEventOptimizer.init(context);
                                this.c.a = true;
                                if (tJConnectListener != null) {
                                    tJConnectListener.onConnectSuccess();
                                }
                            } catch (InterruptedException e) {
                                onConnectFailure();
                            } catch (RuntimeException e2) {
                                TapjoyLog.w("TapjoyAPI", e2.getMessage());
                                onConnectFailure();
                            }
                        }

                        public final void onConnectFailure() {
                            if (tJConnectListener != null) {
                                tJConnectListener.onConnectFailure();
                            }
                        }
                    });
                    this.b = true;
                    if (VERSION.SDK_INT < 14) {
                        TapjoyLog.i("TapjoyAPI", "Automatic session tracking is not available on this device.");
                    } else {
                        if (hashtable != null) {
                            String valueOf = String.valueOf(hashtable.get(TapjoyConnectFlag.DISABLE_AUTOMATIC_SESSION_TRACKING));
                            if (valueOf != null && valueOf.equalsIgnoreCase(TapjoyConstants.TJC_TRUE)) {
                                z = true;
                            }
                        }
                        if (z) {
                            TapjoyLog.i("TapjoyAPI", "Automatic session tracking is disabled.");
                        } else {
                            ej.a(context);
                        }
                    }
                    z = true;
                } catch (TapjoyIntegrationException e) {
                    TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, e.getMessage()));
                    if (tJConnectListener != null) {
                        tJConnectListener.onConnectFailure();
                    }
                } catch (TapjoyException e2) {
                    TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.SDK_ERROR, e2.getMessage()));
                    if (tJConnectListener != null) {
                        tJConnectListener.onConnectFailure();
                    }
                }
            }
        }
        return z;
    }

    public final void e(String str) {
        if (h("enablePaidAppWithActionID")) {
            TapjoyConnectCore.getInstance().enablePaidAppWithActionID(str);
        }
    }

    public final void a(float f) {
        if (i("setCurrencyMultiplier")) {
            TapjoyConnectCore.getInstance().setCurrencyMultiplier(f);
        }
    }

    public final float c() {
        if (i("getCurrencyMultiplier")) {
            return TapjoyConnectCore.getInstance().getCurrencyMultiplier();
        }
        return TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER;
    }

    public final void f(String str) {
        if (h("actionComplete")) {
            TapjoyConnectCore.getInstance().actionComplete(str);
        }
    }

    public final void a(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        if (this.d != null && h("getCurrencyBalance")) {
            this.d.getCurrencyBalance(tJGetCurrencyBalanceListener);
        }
    }

    public final void a(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        if (this.d != null && h("spendCurrency")) {
            this.d.spendCurrency(i, tJSpendCurrencyListener);
        }
    }

    public final void a(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        if (this.d != null && h("awardCurrency")) {
            this.d.awardCurrency(i, tJAwardCurrencyListener);
        }
    }

    public final void a(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        if (this.d != null && h("setEarnedCurrencyListener")) {
            this.d.setEarnedCurrencyListener(tJEarnedCurrencyListener);
        }
    }

    public final void a(TJVideoListener tJVideoListener) {
        if (i("setVideoListener")) {
            TJAdUnit.publisherVideoListener = tJVideoListener;
        }
    }

    public final void a(String str, String str2, double d, String str3) {
        fm a = fm.a();
        if (a.b("trackPurchase")) {
            String a2 = fh.a(str, "trackPurchase", "productId");
            if (a2 != null) {
                String a3 = fh.a(str2, "trackPurchase", "currencyCode");
                if (a3 == null) {
                    return;
                }
                if (a3.length() != 3) {
                    fj.a("trackPurchase", "currencyCode", "invalid currency code");
                    return;
                }
                a.g.a(a2, a3.toUpperCase(Locale.US), d, null, null, fh.b(str3));
                fj.a("trackPurchase called");
            }
        }
    }

    public final void a(String str, String str2, String str3, String str4) {
        fa.a(str, str2, str3, str4);
    }

    public final void a(String str, String str2) {
        fa.a(str, null, null, str2);
    }

    public final void a(String str) {
        fa.a(null, str, null, null, 0);
    }

    public final void a(String str, long j) {
        fa.a(null, str, null, null, j);
    }

    public final void a(String str, String str2, long j) {
        fa.a(str, str2, null, null, j);
    }

    public final void b(String str, String str2, String str3, String str4) {
        fa.a(str, str2, str3, str4, 0);
    }

    public final void a(String str, String str2, String str3, String str4, long j) {
        fa.a(str, str2, str3, str4, j);
    }

    public final void a(String str, String str2, String str3, String str4, String str5, long j) {
        fa.a(str, str2, str3, str4, str5, j, null, 0, null, 0);
    }

    public final void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2) {
        fa.a(str, str2, str3, str4, str5, j, str6, j2, null, 0);
    }

    public final void a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        fa.a(str, str2, str3, str4, str5, j, str6, j2, str7, j3);
    }

    public final void d() {
        if (i("startSession")) {
            if (VERSION.SDK_INT >= 14) {
                ej.a();
            }
            TapjoyConnectCore.getInstance().appResume();
            fa.a();
        }
    }

    public final void e() {
        if (i("endSession")) {
            if (VERSION.SDK_INT >= 14) {
                ej.a();
            }
            fm.a().n = false;
            TapjoyConnectCore.getInstance().appPause();
            fa.b();
        }
    }

    public final void a(Activity activity) {
        if (VERSION.SDK_INT >= 14) {
            ej.a();
        }
        fm.a().n = true;
        fa.a(activity);
    }

    public final void b(Activity activity) {
        if (VERSION.SDK_INT >= 14) {
            ej.a();
        }
        fa.b(activity);
    }

    public final void a(String str, TJSetUserIDListener tJSetUserIDListener) {
        if (i("setUserID")) {
            TapjoyConnectCore.setUserID(str, tJSetUserIDListener);
            fm a = fm.a();
            if (a.c("setUserId")) {
                a.f.b(fh.a(str));
            }
        } else if (tJSetUserIDListener != null) {
            tJSetUserIDListener.onSetUserIDFailure(this.c);
        }
    }

    public final void a(int i) {
        fm a = fm.a();
        if (a.c("setUserLevel")) {
            if (i < -1) {
                i = -1;
            }
            fj.a("setUserLevel({}) called", Integer.valueOf(i));
            a.f.a(i);
        }
    }

    public final void b(int i) {
        fm a = fm.a();
        if (a.c("setUserFriendCount")) {
            fj.a("setUserFriendCount({}) called", Integer.valueOf(i));
            if (i < -1) {
                i = -1;
            }
            a.f.b(i);
        }
    }

    public final void a(int i, String str) {
        fm a = fm.a();
        if (a.c("setUserCohortVariable")) {
            int i2;
            if (i <= 0 || i > 5) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            String str2 = "setCohortVariable: variableIndex is out of range";
            if (fj.a && i2 == 0) {
                fj.b(str2);
            }
            if (i2 != 0) {
                fj.a("setUserCohortVariable({}, {}) called", Integer.valueOf(i), str);
                a.f.a(i, fh.a(str));
            }
        }
    }

    public final Set f() {
        return fm.a().c();
    }

    public final void a(Set set) {
        fm.a().a(set);
    }

    public final void g() {
        fm.a().a(null);
    }

    public final void c(String str) {
        if (!cr.c(str)) {
            fm a = fm.a();
            Set c = a.c();
            if (c.add(str)) {
                a.a(c);
            }
        }
    }

    public final void d(String str) {
        if (!cr.c(str)) {
            fm a = fm.a();
            Set c = a.c();
            if (c.remove(str)) {
                a.a(c);
            }
        }
    }

    public final boolean h() {
        fm a = fm.a();
        if (!a.c("isPushNotificationDisabled")) {
            return false;
        }
        fj.a("isPushNotificationDisabled = {}", Boolean.valueOf(a.f.b.o));
        return a.f.b.o;
    }

    public final void b(boolean z) {
        int i = 0;
        fm a = fm.a();
        if (a.c("setPushNotificationDisabled")) {
            Object[] objArr;
            Boolean valueOf;
            String str;
            Object[] objArr2;
            boolean a2 = a.f.a(z);
            if (a2) {
                objArr = new Object[1];
                valueOf = Boolean.valueOf(z);
                str = "setPushNotificationDisabled({}) called";
                objArr2 = objArr;
            } else {
                str = "setPushNotificationDisabled({}) called, but it is already {}";
                objArr2 = new Object[2];
                objArr2[0] = Boolean.valueOf(z);
                if (z) {
                    valueOf = "disabled";
                    i = 1;
                    objArr = objArr2;
                } else {
                    valueOf = String.ENABLED;
                    i = 1;
                    objArr = objArr2;
                }
            }
            objArr[i] = valueOf;
            fj.a(str, objArr2);
            if (a2 && a.k && !cr.c(a.d)) {
                String str2;
                if (a.o != null) {
                    str2 = null;
                } else {
                    r b = fo.b(a.e);
                    str2 = cr.b(b.b.b(b.a));
                }
                a.a(str2);
            }
        }
    }

    public final boolean i() {
        return this.a;
    }

    private boolean h(String str) {
        if (this.a) {
            return true;
        }
        TapjoyLog.w("TapjoyAPI", "Can not call " + str + " because Tapjoy SDK has not successfully connected.");
        return false;
    }

    private boolean i(String str) {
        if (this.b) {
            return true;
        }
        this.c = "Can not call " + str + " because Tapjoy SDK is not initialized.";
        TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, this.c));
        return false;
    }

    public final void a(String str, String str2, String str3, String str4, Map map) {
        fm a = fm.a();
        if (a.b("trackEvent") && !cr.c(str2)) {
            Map b = cv.b();
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    Object key = entry.getKey();
                    String str5;
                    if (key == null) {
                        String str6 = "trackEvent";
                        str5 = "key in values map";
                        if (fj.a) {
                            ac.a(MovieReward_6005.ADNETWORK_NAME, "{}: {} must not be null", str6, str5);
                            return;
                        }
                        return;
                    } else if (key instanceof String) {
                        str5 = fh.a((String) key, "trackEvent", "key in values map");
                        if (str5 != null) {
                            Object value = entry.getValue();
                            if (value instanceof Number) {
                                b.put(str5, Long.valueOf(((Number) value).longValue()));
                            } else {
                                fj.a("trackEvent", "value in values map", "must be a long");
                                return;
                            }
                        }
                        return;
                    }
                }
            }
            a.g.a(str, str2, str3, str4, b);
            fj.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, b);
        }
    }

    public final void b(String str) {
        fm a = fm.a();
        if (a.c("setAppDataVersion")) {
            a.f.a(fh.a(str));
        }
    }

    public final void g(String str) {
        fm a = fm.a();
        fj.a("setGcmSender({}) called", str);
        a.d = cr.a(str);
        a.b();
    }

    public final void a(GLSurfaceView gLSurfaceView) {
        fm.a();
        fm.a(gLSurfaceView);
    }
}