package com.tapjoy.internal;

import android.app.Activity;

public abstract class fg {
    public static fg a;

    public abstract void a(Activity activity);

    public static void b(Activity activity) {
        if (a != null) {
            a.a(activity);
        }
    }
}