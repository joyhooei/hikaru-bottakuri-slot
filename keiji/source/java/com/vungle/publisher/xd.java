package com.vungle.publisher;

import com.vungle.publisher.xb.a;
import dagger.MembersInjector;
import dagger.a.c;
import dagger.a.d;

/* compiled from: vungle */
public final class xd implements c<a> {
    static final /* synthetic */ boolean a = (!xd.class.desiredAssertionStatus());
    private final MembersInjector<a> b;

    public final /* synthetic */ Object get() {
        return (a) d.a(this.b, new a());
    }

    private xd(MembersInjector<a> membersInjector) {
        if (a || membersInjector != null) {
            this.b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public static c<a> a(MembersInjector<a> membersInjector) {
        return new xd(membersInjector);
    }
}
