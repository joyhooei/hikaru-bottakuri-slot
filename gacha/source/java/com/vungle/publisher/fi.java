package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fi implements MembersInjector<fe> {
    static final /* synthetic */ boolean a = (!fi.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        fe feVar = (fe) obj;
        if (feVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        feVar.u = (cf) this.b.get();
        feVar.e = (a) this.c.get();
    }

    private fi(Provider<cf> provider, Provider<a> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<fe> a(Provider<cf> provider, Provider<a> provider2) {
        return new fi(provider, provider2);
    }
}