package com.vungle.publisher;

import com.vungle.publisher.dx.c;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kc implements MembersInjector<jy> {
    static final /* synthetic */ boolean a = (!kc.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<c> c;
    private final Provider<agt> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        jy jyVar = (jy) obj;
        if (jyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        jyVar.a = (cf) this.b.get();
        jyVar.b = (c) this.c.get();
        jyVar.c = (agt) this.d.get();
    }

    private kc(Provider<cf> provider, Provider<c> provider2, Provider<agt> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<jy> a(Provider<cf> provider, Provider<c> provider2, Provider<agt> provider3) {
        return new kc(provider, provider2, provider3);
    }
}